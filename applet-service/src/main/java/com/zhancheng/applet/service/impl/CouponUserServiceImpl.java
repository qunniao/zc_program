package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.CouponUserService;
import com.zhancheng.core.dao.CouponUserMapper;
import com.zhancheng.core.entity.CouponUser;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户已有的优惠券 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-16
 */
@Service
public class CouponUserServiceImpl extends ServiceImpl<CouponUserMapper, CouponUser> implements CouponUserService {

}
