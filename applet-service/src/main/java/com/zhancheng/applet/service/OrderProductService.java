package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.OrderProduct;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单商品表 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface OrderProductService extends IService<OrderProduct> {


    /**
     * 获取订单产品信息
     *
     * @param orderNumber
     * @return
     */
    String getProduct(String orderNumber);

    /**
     * 根据orderNum获取所有的订单商品
     *
     * @param orderNum
     * @return
     */
    List<Map<String, Object>> getOrderProductByOrderNum(String orderNum);
}
