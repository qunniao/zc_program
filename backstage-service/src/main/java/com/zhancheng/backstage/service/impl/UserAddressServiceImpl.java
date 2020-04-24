package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.UserAddressService;
import com.zhancheng.core.dao.UserAddressMapper;
import com.zhancheng.core.entity.UserAddress;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户地址 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

}
