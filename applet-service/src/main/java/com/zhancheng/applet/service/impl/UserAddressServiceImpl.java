package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.UserAddressService;
import com.zhancheng.core.dao.UserAddressMapper;
import com.zhancheng.core.dto.PageDto;
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

    @Override
    public IPage queryPage(PageDto<UserAddress> pageDto, UserAddress userAddress) {

        IPage userAddressPages = baseMapper.selectPage(pageDto.getPage(),
                new QueryWrapper<UserAddress>().eq("is_deleted", 0)
                        .eq("uid", userAddress.getUid())
                        .orderByDesc("is_default_address")
                        .orderByDesc("gmt_modified"));

        return userAddressPages;
    }

    @Override
    public UserAddress queryDefault(Long uid) {

        return baseMapper.selectOne(new QueryWrapper<UserAddress>()
                .eq("uid", uid).eq("is_default_address", 1).eq("is_deleted", 0));
    }
}
