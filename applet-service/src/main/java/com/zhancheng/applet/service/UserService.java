package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.User;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户Id查询个人信息
     *
     * @param id
     * @return 个人信息
     */
    User getUser(Integer id);

    /**
     * 根据token获得UserId
     *
     * @return
     */
    Long getUserId();
}
