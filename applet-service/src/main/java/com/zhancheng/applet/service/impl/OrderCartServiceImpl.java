package com.zhancheng.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.OrderCartService;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.OrderCartMapper;
import com.zhancheng.core.dao.ProductGuigeSkuMapper;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.entity.OrderCart;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.entity.ProductGuigeSku;
import com.zhancheng.core.vo.OrderCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class OrderCartServiceImpl extends ServiceImpl<OrderCartMapper, OrderCart> implements OrderCartService {


    @Autowired
    private OrderCartMapper orderCartMapper;

    @Autowired
    private ProductGuigeSkuMapper productGuigeSkuMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public String addCart(OrderCart orderCart) {

        OrderCart orderCartInfo;
        // 购买的产品数量
        Integer productNum = orderCart.getProductNum();
        //库存
        Integer num;

        //商品信息
        Product product = productMapper.selectById(orderCart.getSpuId());

        if (ObjectUtil.isNull(product)){
            throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
        }
        // sku信息
        ProductGuigeSku productGuigeSku = new ProductGuigeSku();

        // 1代表 产品， 2,代表 sku;
        int type;
        // 如果没有sku
        if (ObjectUtil.isNull(orderCart.getSkuId())) {
            orderCartInfo = orderCartMapper.selectOne(new QueryWrapper<OrderCart>()
                    .eq("uid", orderCart.getUid())
                    .eq("spu_id", orderCart.getSpuId())
                    .eq("is_deleted", 0));
            num = product.getStore();
            type = 1;
        }else {
            orderCartInfo = orderCartMapper.selectOne(new QueryWrapper<OrderCart>()
                    .eq("uid", orderCart.getUid())
                    .eq("sku_id", orderCart.getSkuId())
                    .eq("is_deleted", 0));
            productGuigeSku = productGuigeSkuMapper.selectById(orderCart.getSkuId());
            num = productGuigeSku.getNum();
            type = 2;
        }

        //如果有购物车数据就加购
        if (ObjectUtil.isNotNull(orderCartInfo)) {
            //增加后的数量
            int addNum = orderCart.getProductNum() + productNum;
            //购物车每个sku的商品数量最少是1 ||加购数量小于库存
            if (addNum < 1 || num < addNum) {
                throw new MyException(CodeMsg.GOODS_NUM_ERROR);
            }
            orderCartInfo.setProductNum(addNum);
            orderCartMapper.updateById(orderCartInfo);
        }else if(ObjectUtil.isNull(orderCartInfo)) {

            //购物车每个sku的商品数量最少是1 ||加购数量小于库存
            if (productNum < 1 || num < productNum) {
                throw new MyException(CodeMsg.GOODS_NUM_ERROR);
            }

            BigDecimal price;
            if (type == 1){
                price = product.getPrice();
            }else {
                price = productGuigeSku.getPrice();
            }
            orderCart.setProductPrice(price);
            orderCart.setSpuId(product.getPid());
            orderCart.setProductName(product.getProductName());
            orderCart.insert();
        }

        return R.ok();
    }

    @Override
    public Boolean updateCart(OrderCart orderCart) {

        OrderCart orderCartInfo = baseMapper.selectById(orderCart.getCid());

        if (ObjectUtil.isNull(orderCartInfo)) {
            throw new MyException(CodeMsg.NOT_EXISTED);
        }

        // 产品数量
        int productNum = orderCart.getProductNum();

        // 查询 sku
        ProductGuigeSku productGuigeSku = productGuigeSkuMapper.selectById(orderCart.getSkuId());

        if (ObjectUtil.isNull(productGuigeSku)) {
            throw new MyException(CodeMsg.SKU_NOT_EXISTED);
        }

        Integer num = productGuigeSku.getNum();

        // 判断产品数量是否 大于  0 并且不超过库存
        if (productNum > num || productNum < 1) {
            throw new MyException(CodeMsg.GOODS_NUM_ERROR);
        }

        // 单价 * 数量 得出总价格
        BigDecimal multiply = new BigDecimal(productNum).multiply(productGuigeSku.getPrice());

        // 修改购物车
        return orderCartInfo.setProductNum(productNum).setProductPrice(multiply).updateById();
    }

    @Override
    public List<OrderCartVo> queryCart(Long uid) {

        return baseMapper.queryCart(uid);
    }
}
