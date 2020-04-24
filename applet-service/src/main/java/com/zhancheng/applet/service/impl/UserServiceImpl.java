package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HttpServletRequest request;

    @Override
    public User getUser(Integer id) {

        return userMapper.selectById(id);
    }

    @Override
    public Long getUserId() {
        String token = request.getHeader("token");
        return redisTemplate.getUid(token);
    }
}
