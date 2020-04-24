package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.OrderProductService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.entity.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单商品表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements OrderProductService {

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Override
    public String getOrderProductByOrderNumber(String orderNum) {
        List<Map<String, Object>> product = orderProductMapper.getOrderProductByOrderNum(orderNum);
        return R.ok(product);
    }
}
