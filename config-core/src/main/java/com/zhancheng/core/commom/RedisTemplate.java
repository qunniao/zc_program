package com.zhancheng.core.commom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhancheng.core.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author tangchao
 */

@Component
public class RedisTemplate extends StringRedisTemplate {

    @Autowired
    public RedisTemplate(RedisConnectionFactory connectionFactory) {
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

    /**
     * 过期时间(秒为单位)
     */
    private long expireTime = 30 * 24 * 60 * 60;


    /**
     * 通过key获取value，并重置过期时间
     *
     * @param key key
     * @return value
     */
    public String get(String key) {
        String value = super.opsForValue().get(key);
        if (!StringUtils.isBlank(value)) {
            super.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 存储数据
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, String value) {
        super.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }


    /**
     * 存储手机验证码
     *
     * @param key   键
     * @param value 值
     */
    public void setSmsCode(String key, String value) {
        super.opsForValue().set(key, value, 320, TimeUnit.SECONDS);
    }


    /**
     * 存储用户信息
     *
     * @param user 用户信息
     * @return 用户token
     */
    public String setUser(User user) {
//        判断用户是否已经存入redis,如果有则删除信息
        String oldToken = get("uid:" + user.getUid());
        if (oldToken != null) {
            super.delete("token:" + oldToken);
        }
        String token = SignUtil.generateNonceStr();
        Long uid = user.getUid();
        //存储id : token
        set("uid:" + uid, token);
        Map userMap = new HashMap<String, String>();
        userMap.put("uid", user.getUid());
        userMap.put("phone", user.getPhone());
        userMap.put("nickname", user.getUsername());
        userMap.put("gender", user.getGender());
        userMap.put("cover", user.getCover());
        userMap.put("status", user.getStatus());
        userMap.put("signature", user.getSignature());
        String userString = JSONObject.toJSONString(userMap, SerializerFeature.WriteMapNullValue);
        //存储token : 用户信息
        set("token:" + token, userString);
        return token;
    }

    /**
     * 传入token,返回用户信息
     *
     * @param token
     * @return 返回用户信息, 如果没有则返回null
     */
    public JSONObject getUser(String token) {
        String s = get("token:" + token);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(s);
        return jsonObject;
    }


    /**
     * 查询用户Id
     *
     * @param token
     * @return 返回用户Id
     */
    public Long getUid(String token) {
        String s = get("token:" + token);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(s);
        return jsonObject.getLong("uid");
    }


}
