package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.UserAddress;

/**
 * <p>
 * 用户地址 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface UserAddressService extends IService<UserAddress> {

    /**
     * 分页查询列表
     * @param pageDto 分页参数
     * @param userAddress 用户地址参数
     * @return
     */
    IPage<UserAddress> queryPage(PageDto<UserAddress> pageDto, UserAddress userAddress);

    /**
     * 查询默认地址
     * @param uid 用户id
     * @return 默认地址信息
     */
    UserAddress queryDefault(Long uid);

}
