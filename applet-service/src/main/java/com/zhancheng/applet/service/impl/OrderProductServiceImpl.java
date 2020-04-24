package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.OrderProductService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.entity.OrderProduct;
import com.zhancheng.core.entity.Product;
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

    @Autowired
    private ProductMapper productMapper;

    @Override
    public String getProduct(String orderNumber) {
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("order_number", orderNumber);
        List<OrderProduct> orderProducts = orderProductMapper.selectByMap(hashMap);
        if (orderProducts == null) {
            throw new MyException(CodeMsg.QUERY_EMPTY);
        }
        OrderProduct orderProduct = orderProducts.get(0);
        Long spuId = orderProduct.getSpuId();
        Product product = productMapper.selectById(spuId);
        return R.ok(product);
    }

    @Override
    public List<Map<String, Object>> getOrderProductByOrderNum(String orderNum) {

        return orderProductMapper.getOrderProductByOrderNum(orderNum);
    }
}
