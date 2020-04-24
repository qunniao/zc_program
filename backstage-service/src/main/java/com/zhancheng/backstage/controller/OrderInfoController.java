package com.zhancheng.backstage.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhancheng.backstage.service.OrderInfoService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.OrderInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "订单表相关")
@RestController
@RequestMapping("{version}/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping
    @ApiOperation(value = "获取订单发货详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(name = "size", value = "页容量", required = true)
    })
    public String getOrderInfo(long pageNum, long size) {
        return orderInfoService.getOrderInfo(pageNum, size);
    }


    @Verify(role = UserIdentity.ADMIN)
    @GetMapping("/order")
    @ApiOperation(value = "查询订单表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", required = true),
            @ApiImplicitParam(name = "size", value = "页容量", required = true),
            @ApiImplicitParam(name = "orderState", value = "为空则查询所有状态的订单(订单状态, -1 已关闭，0代付款，1服务中，3待评价)"),
            @ApiImplicitParam(name = "uid", value = "用户id")
    })
    public String getOrder(long pageNum, long size, Integer orderState, Long uid) {
        return orderInfoService.getOrder(pageNum, size, orderState, uid);
    }

    @Verify(role = UserIdentity.ADMIN)
    @DeleteMapping
    @ApiOperation(value = "删除订单")
    @ApiImplicitParam(name = "oids", value = "订单Id数组", required = true)
    public String deleteOrderInfo(Long[] oids) {
        for (Long oid : oids) {
            OrderInfo temp = new OrderInfo();
            temp.setOid(oid);
            temp.setIsDeleted(true);
            orderInfoService.updateById(temp);
        }
        return R.ok();
    }

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping("/One")
    @ApiOperation(value = "查询单个订单")
    @ApiImplicitParam(name = "oid", value = "订单Id", required = true)
    public String selectOrderInfoById(Long oid) {
        return R.ok(orderInfoService.getById(oid));
    }

    @Verify(role = UserIdentity.ADMIN)

    @PutMapping("/shipments")
    @ApiOperation(value = "发货接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oid", value = "订单Id", required = true),
            @ApiImplicitParam(name = "sendWay", value = "配送方式", required = true)
    })
    public String shipments(Long oid, String sendWay) {

        boolean update = orderInfoService.update(new UpdateWrapper<OrderInfo>()
                .eq("oid", oid)
                .set("send_way", sendWay)
                .set("order_state", 2));
        return R.ok(update);
    }

}

