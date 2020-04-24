package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.UserCardService;
import com.zhancheng.core.dao.UserCardMapper;
import com.zhancheng.core.entity.UserCard;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户名片 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class UserCardServiceImpl extends ServiceImpl<UserCardMapper, UserCard> implements UserCardService {

}
