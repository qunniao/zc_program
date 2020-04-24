package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.OrderInfoService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.OrderInfoMapper;
import com.zhancheng.core.entity.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {


    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public String getOrderInfo(long pageNum, long size) {
        IPage<Map<String, Object>> orderInfo = orderInfoMapper.getOrderInfo(new Page<>(pageNum, size));
        return R.ok(orderInfo);
    }

    @Override
    public String getOrder(long pageNum, long size, Integer orderState, Long uid) {

        IPage<Map<String, Object>> order = orderInfoMapper.getOrderByState(new Page(pageNum, size), orderState, uid);

        return R.ok(order);
    }
}