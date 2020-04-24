package com.zhancheng.applet.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zhancheng.applet.common.RequestUtil;
import com.zhancheng.applet.service.OrderInfoService;
import com.zhancheng.applet.service.OrderProductService;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.OrderInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "订单相关")
@RestController
@RequestMapping("{version}/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private UserService userService;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * 微信支付异步通知函数
     *
     * @param notifyXml
     * @param response
     */
    @Verify
    @PostMapping("/wxPayUnifiedNotify")
    public void wxPayUnifiedNotify(@RequestBody String notifyXml, HttpServletResponse response) {
        String result = orderInfoService.wxPayUnifiedNotify(notifyXml);
        LOG.info("回调result" + result);
        response.setCharacterEncoding("utf-8");
        RequestUtil.writeResp(response, result);
    }

    /**
     * 微信支付统一下单API
     *
     * @param
     * @return
     * @throws Exception
     */
    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "商品支付", notes = "无")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNumber", value = "订单编号", required = true)
    })
    @PostMapping("/wxPayOrder")
    public String wxPay(String orderNumber) throws Exception {
        Long userId = userService.getUserId();
        return orderInfoService.wxPay(orderNumber, userId);
    }

    @Verify(role = UserIdentity.ORDINARY)
    @GetMapping
    @ApiOperation(value = "获取订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderState", value = "如果不传则查询所有订单,0代付款，1服务中，3待评价")
    })
    public String getOrderInfo(Integer orderState) {

        List result = new ArrayList();
        Map<String, Object> hashMap = MapFactory.getHashMap();
        Long userId = userService.getUserId();
        hashMap.put("uid", userId);

//        hashMap.put("omid", "0");
        if (orderState != null) {
            hashMap.put("order_state", orderState);
        }
        Collection<OrderInfo> orderInfos = orderInfoService.listByMap(hashMap);
        System.out.println(orderInfos);
        Iterator<OrderInfo> iterator = orderInfos.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> temp = new HashMap();
            OrderInfo next = iterator.next();
            temp.put("order", next);
            System.err.println(next.getOrderNumber());
            List<Map<String, Object>> orderProductByOrderNum = orderProductService.getOrderProductByOrderNum(next.getOrderNumber());
            temp.put("orderProduct", orderProductByOrderNum);
            result.add(temp);
        }

        return R.ok(result);
    }

//    @Verify(role = UserIdentity.ORDINARY)
    @PostMapping
    @ApiOperation(value = "提交订单,返回订单号", notes = "提交格式为json,例如:{\"skuInfo\":[{\"skuid\":1,\"num\":2},{\"skuid\":1,\"num\":2}],\"cid\":1}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "产品id", required = true),
            @ApiImplicitParam(name = "skuId", value = "skuId"),
            @ApiImplicitParam(name = "num", value = "商品数量"),
            @ApiImplicitParam(name = "cid", value = "优惠券id"),
    })
    public String saveOrderInfo(@RequestBody String json) {
        JSONObject jsonObject = JSONUtil.parseObj(json);
        Long userId = userService.getUserId();
        return orderInfoService.saveOrderInfo(userId, jsonObject);
    }

    @Verify(role = UserIdentity.ORDINARY)
    @PatchMapping("/finish")
    @ApiOperation(value = "完成订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNumber", value = "订单号"),
    })
    public String finish(String orderNumber) {
        Map<String, Object> map = MapFactory.getHashMap();
        map.put("order_state", "1");
        Collection<OrderInfo> orderInfos = orderInfoService.listByMap(map);
        if (orderInfos == null) {
            throw new MyException(CodeMsg.QUERY_EMPTY);
        }
        Iterator<OrderInfo> iterator = orderInfos.iterator();
        OrderInfo next = iterator.next();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOid(next.getOid());
        orderInfo.setOrderState(3);
        orderInfoService.updateById(orderInfo);
        return R.ok();
    }

}

