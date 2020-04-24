package com.zhancheng.backstage.controller;


import com.zhancheng.backstage.service.OrderProductService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.UserIdentity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单商品表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "订单商品表相关")
@RestController
@RequestMapping("{version}/orderProduct")
public class OrderProductController {

    @Autowired
    private OrderProductService orderProductService;

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping
    @ApiOperation(value = "查询该订单的商品")
    @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true)
    public String getOrderProduct(String orderNumber) {
        return orderProductService.getOrderProductByOrderNumber(orderNumber);
    }


}

