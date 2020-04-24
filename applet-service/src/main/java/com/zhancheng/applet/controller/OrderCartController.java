package com.zhancheng.applet.controller;


import com.zhancheng.applet.service.OrderCartService;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.OrderCart;
import com.zhancheng.core.vo.OrderCartVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "购物车相关")
@RestController
@RequestMapping("{version}/orderCart")
public class OrderCartController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderCartService orderCartService;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Verify(role = UserIdentity.ORDINARY)
    @PostMapping
    @ApiOperation(value = "添加购物车", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "skuId, 有则传，没有为空"),
            @ApiImplicitParam(name = "spuId", value = "产品id",required = true),
            @ApiImplicitParam(name = "productNum", value = "加购数量", required = true)
    })
    public String addCart(@Valid OrderCart orderCart) {
        String token = request.getHeader("token");
        orderCart.setUid(redisTemplate.getUid(token));
        if (orderCart.getProductNum() == null) {
            orderCart.setProductNum(1);
        }
        return orderCartService.addCart(orderCart);
    }

    @Verify(role = UserIdentity.ORDINARY)
    @GetMapping
    @ApiOperation(value = "查询购物车列表")
    public List<OrderCartVo> getCart() {
        String token = request.getHeader("token");
        Long uid = redisTemplate.getUid(token);

        return orderCartService.queryCart(uid);

    }

    @Verify(role = UserIdentity.ORDINARY)
    @PutMapping("/update")
    @ApiOperation(value = "修改购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "购物车id", required = true),
            @ApiImplicitParam(name = "productNum", value = "产品数量", required = true),
            @ApiImplicitParam(name = "skuId", value = "skuId", required = true)
    })
    public String updateCart(OrderCart orderCart) {

        return R.ok(orderCartService.updateCart(orderCart));
    }

    @Verify(role = UserIdentity.ORDINARY)
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cIds", value = "购物车id集合", required = true)
    })
    public String deleteCart(@RequestParam List<Long> cIds) {

        return R.ok(orderCartService.removeByIds(cIds));
    }


}

