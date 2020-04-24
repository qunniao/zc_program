package com.zhancheng.core.config.security;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Demon
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {
    /**
     * 路径中版本的前缀， 这里用 /v[1-9]/的形式
     */
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v([-+]?[0-9]*\\.?[0-9]+)/");

    /**
     * api的版本
     */
    private double apiVersion;

    public ApiVersionCondition(double apiVersion) {
        this.apiVersion = apiVersion;
    }


    /**
     * 将不同的筛选条件合并
     *
     * @param apiVersionCondition
     * @return
     */
    @Override
    public ApiVersionCondition combine(ApiVersionCondition apiVersionCondition) {
        //return null;
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVersionCondition(apiVersionCondition.getApiVersion());
    }

    /**
     * 根据request查找匹配到的筛选条件
     *
     * @param httpServletRequest
     * @return
     */
    @Nullable
    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        //return null;
        Matcher m = VERSION_PREFIX_PATTERN.matcher(httpServletRequest.getRequestURI());
        if (m.find()) {
            Double version = Double.valueOf(m.group(1));
            if (version >= this.apiVersion) {
                return this;
            }
        }
        return null;
    }


    /**
     * 不同筛选条件比较,用于排序
     *
     * @param apiVersionCondition
     * @param httpServletRequest
     * @return
     */
    @Override
    public int compareTo(ApiVersionCondition apiVersionCondition, HttpServletRequest httpServletRequest) {
        //return 0;
        // 优先匹配最新的版本号
        return (int) (apiVersionCondition.getApiVersion() - this.apiVersion);
    }

    public double getApiVersion() {
        return apiVersion;
    }


}