package com.zhancheng.backstage.service;

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


    String getOrderInfo(long pageNum, long size);


    /**
     * 查询订单表
     *
     * @param pageNum    页数
     * @param size       大小
     * @param orderState 订单状态
     * @param uid 用户id
     * @return
     */
    String getOrder(long pageNum, long size, Integer orderState, Long uid);

}
