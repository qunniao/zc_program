package com.zhancheng.applet.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhancheng.applet.common.SMSUtil;
import com.zhancheng.applet.common.WxUtil;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "用户个人信息相关")
@RestController
@RequestMapping("{version}/user")
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private WxUtil wxUtil;

    @Verify
    @PostMapping("/wxLogin")
    @ApiOperation(value = "微信登录", notes = "传入从微信获得code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "凭证", required = true),
            @ApiImplicitParam(name = "encryptedData", value = "包括敏感数据在内的完整用户信息的加密数据", required = true),
            @ApiImplicitParam(name = "iv", value = "加密算法的初始向量", required = true)
    })
    public String wxLogin(String code, String encryptedData, String iv) throws Exception {
        JSONObject userInfo = wxUtil.loginByWeixin(code, encryptedData, iv);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("openid", userInfo.getString("openid"));
        hashMap.put("status", 1);
        Collection<User> users = userService.listByMap(hashMap);
        Iterator<User> iterator = users.iterator();
        if (users.size() > 0) {
            User next = iterator.next();
            //判断用户是否被禁用
            if (next.getIsDeleted()) {
                throw new MyException(CodeMsg.USER_DISABLED);
            }
            //判断手机号是否为空
            if (StringUtils.isBlank(next.getPhone())) {
                String token = redisTemplate.setUser(next);
                Map<String, Object> map = new HashMap<>();
                map.put("token", token);
                map.put("alert", "手机号为空,需要填入手机号");
                return R.ok(map);
            }
            User user = new User();
            user.setUid(next.getUid());
            user.setCover(userInfo.getString("avatarUrl"));
            user.setGmtLogin(new Date());
            userService.saveOrUpdate(user);
            next.setCover(userInfo.getString("avatarUrl"));
            String token = redisTemplate.setUser(next);
            return R.ok(token);
        } else {
            User user = new User();
            user.setNickname(userInfo.getString("nickName"));
            user.setOpenid(userInfo.getString("openid"));
            user.setGender(userInfo.getInteger("gender"));
            user.setStatus("1");
            user.setCover(userInfo.getString("avatarUrl"));
            user.setGmtLogin(new Date());
            userService.save(user);
            String token = redisTemplate.setUser(user);
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("alert", "手机号为空,需要填入手机号");
            return R.ok(map);
        }

    }


    @Verify
    @GetMapping("/verification")
    @ApiOperation(value = "获取手机验证码", notes = "验证码有效时间5分钟")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true)
    })
    public String verification(String phone) throws Exception {
        if (StringUtils.isBlank(phone)) {
            return R.fail(CodeMsg.PHONE_NUMBER_EMPTY);
        }
        String code = SMSUtil.getCode();
        boolean flag = SMSUtil.sendAliSMS(phone, "SMS_161295122", code);
        if (!flag) {
            return R.fail(CodeMsg.ALIYUN_ERROR);
        }
        redisTemplate.setSmsCode("sms:" + phone, code);
        return R.ok();
    }


    @Verify(role = UserIdentity.ORDINARY)
    @PatchMapping("/phoneNumber")
    @ApiOperation(value = "修改用户手机号码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true),
            @ApiImplicitParam(name = "verificationCode ", value = "手机验证码", required = true)
    })
    public String updatePhoneNumber(String phone, String verificationCode) throws Exception {
        if (StringUtils.isBlank(verificationCode)) {
            R.fail(CodeMsg.VERIFICATION_CODE_ERROR);
        }
        String s = redisTemplate.get("sms:" + phone);
        if (verificationCode.equals(s)) {
            //根据token修改手机号
            User user = new User();
            user.setUid(userService.getUserId());
            user.setPhone(phone);
            userService.saveOrUpdate(user);

        } else {
            R.fail(CodeMsg.VERIFICATION_CODE_ERROR);
        }

        return R.ok();
    }


    @Verify
    @GetMapping
    @ApiOperation(value = "查询用户个人信息", notes = "返回个人详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "根据用户Id查询用户个人信息,如果id为空,则根据token查询")
    })
    public String getUser(String id) {
        if (StringUtils.isBlank(id)) {
            String token = request.getHeader("token");
            if (StringUtils.isBlank(token)) {
                R.fail(CodeMsg.TOKEN_IS_NULL);
            }
            JSONObject jsonUser = redisTemplate.getUser(token);
            if (StringUtils.isBlank(jsonUser.toJSONString())) {
                R.fail(CodeMsg.TOKENG_ABNORMAL);
            }
            id = jsonUser.getString("uid");

        }
        User user = userService.getById(id);
        return R.ok(user);
    }
}

