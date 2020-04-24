package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.OrderCart;
import com.zhancheng.core.vo.OrderCartVo;

import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface OrderCartService extends IService<OrderCart> {

    /**
     * 添加购物车
     *
     * @param orderCart
     * @return
     */
    String addCart(OrderCart orderCart);

    /**
     * 修改 购物车
     * @param orderCart 购物车信息
     * @return
     */
    Boolean updateCart(OrderCart orderCart);

    /**
     * 查询购物车列表
     *
     * @param uid  用户id
     * @return boolean
     */
    List<OrderCartVo> queryCart(Long uid);

}
