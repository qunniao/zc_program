package com.zhancheng.core.config.security;

import com.alibaba.fastjson.JSONObject;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.UserIdentity;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by tangchao on 2018/1/26  17:08
 */
@Aspect
@Component
public class MyAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate redisTemplate;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 在所有标注@Verify的地方切入
     *
     * @param joinPoint 切入点
     */
    @Before("@annotation(com.zhancheng.core.config.security.Verify)")
    public void beforeExec(JoinPoint joinPoint) {
        logger.info("**************************************");
        logger.info(getMethodName(joinPoint) + ":start");
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String role = method.getAnnotation(Verify.class).role();
        // 判断是否要验证权限
        if (!role.equals(UserIdentity.NOT)) {
            String token = request.getHeader("token");
            //判断token是否为空
            if (StringUtils.isBlank(token)) {
                throw new MyException(CodeMsg.TOKEN_IS_NULL);
            }
            JSONObject user = redisTemplate.getUser(token);
            if (user == null) {
                throw new MyException((CodeMsg.TOKENG_ABNORMAL));
            }
            String status = user.getString("status");
            //判断该token是否存在
            if (StringUtils.isBlank(status)) {
                throw new MyException((CodeMsg.TOKENG_ABNORMAL));
            }
            //判断是否有权限
            else if (!status.equals(role)) {
                throw new MyException((CodeMsg.PERMISSION_ERROR));
            }

        }

    }

    /**
     * 正常返回
     *
     * @param joinPoint
     */
    @AfterReturning(value = "@annotation(com.zhancheng.core.config.security.Verify)")
    public void after(JoinPoint joinPoint) {
        logger.info(getMethodName(joinPoint) + ":end");
        logger.info("**************************************");
    }


    /**
     * 异常返回
     *
     * @param
     */
    @AfterThrowing(value = "@annotation(com.zhancheng.core.config.security.Verify)", throwing = "ex")
    public void afterEx(Exception ex) {
        ex.printStackTrace();

        logger.error(ex.getMessage());
        logger.error("**************************************");
    }

    /**
     * 获得请求方法的路径
     *
     * @param joinPoint 传入JoinPoin
     * @return 截取类名+方法名
     */
    public static String getMethodName(JoinPoint joinPoint) {
        String str = joinPoint.getSignature().toString();
        int i = str.lastIndexOf(".");
        i = str.substring(0, i).lastIndexOf(".");
        String result = str.substring(i + 1);
        return result;
    }
}
