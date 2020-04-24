package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.CouponService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.CouponMapper;
import com.zhancheng.core.dao.CouponUserMapper;
import com.zhancheng.core.entity.Coupon;
import com.zhancheng.core.entity.CouponUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CouponUserMapper couponUserMapper;

    @Override
    public String getCoupon(Long uid) {
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("uid", uid);
        hashMap.put("is_use", 0);
        List<CouponUser> couponUsers = couponUserMapper.selectByMap(hashMap);
        if (couponUsers == null || couponUsers.size() <= 0) {
            return R.ok();
        }
        List lists = new ArrayList();
        couponUsers.stream().forEach(x -> {
            lists.add(x.getCid());
        });
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().in(Coupon::getCid, lists).eq(Coupon::getIsDeleted, false).orderByDesc(Coupon::getGmtCreate);
        return R.ok(list(queryWrapper));
    }
}
