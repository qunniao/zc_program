package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.CouponService;
import com.zhancheng.core.dao.CouponMapper;
import com.zhancheng.core.entity.Coupon;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家发布的优惠券 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-16
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

}
