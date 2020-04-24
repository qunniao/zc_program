package com.zhancheng.applet.controller;


import com.zhancheng.applet.service.CouponService;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.UserIdentity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商家发布的优惠券 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-16
 */
@Api(tags = "商家发布的优惠券相关")
@RestController
@RequestMapping("{version}/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private UserService userService;

    @GetMapping
    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "获取用户优惠券")
    public String getCouponController() {
        Long userId = userService.getUserId();
        return couponService.getCoupon(userId);
    }


}

