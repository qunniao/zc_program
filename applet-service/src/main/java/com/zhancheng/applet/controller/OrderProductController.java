package com.zhancheng.applet.controller;


import com.zhancheng.applet.service.OrderProductService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.UserIdentity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@Api(tags = "订单商品")
@RestController
@RequestMapping("{version}/orderProduct")
public class OrderProductController {

    @Autowired
    private OrderProductService productService;

    @GetMapping
    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "获取订单详细信息", notes = "无")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNumber", value = "订单编号", required = true)
    })
    public String getProduct(String orderNumber) {
        return productService.getProduct(orderNumber);
    }


}

