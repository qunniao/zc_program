package com.zhancheng.applet.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.OrderInfo;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface OrderInfoService extends IService<OrderInfo> {


    /**
     * 用户下单
     *
     * @param uid        用户id
     * @param jsonObject 下单相关参数
     * @return 订单号
     */
    String saveOrderInfo(Long uid, JSONObject jsonObject);

    /**
     * 微信统一下单
     *
     * @param orderNumber 订单号
     * @param uid         用户id
     * @return
     */
    String wxPay(String orderNumber, Long uid) throws Exception;


    /**
     * 微信回调方法
     *
     * @param notifyXml
     * @return
     */
    String wxPayUnifiedNotify(String notifyXml);


}
