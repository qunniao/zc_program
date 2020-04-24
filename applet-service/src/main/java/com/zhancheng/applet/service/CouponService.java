package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.Coupon;

/**
 * <p>
 * 商家发布的优惠券 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-16
 */
public interface CouponService extends IService<Coupon> {

    /**
     * 获取用户的优惠券
     *
     * @param uid
     * @return
     */
    String getCoupon(Long uid);
}
