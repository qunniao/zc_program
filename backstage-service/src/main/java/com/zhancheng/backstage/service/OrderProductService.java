package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.OrderProduct;

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
     * 根据订单号查询购买的商品
     *
     * @return
     */
    String getOrderProductByOrderNumber(String orderNum);
}
