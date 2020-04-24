package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.OrderCart;
import com.zhancheng.core.vo.OrderCartVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Repository
public interface OrderCartMapper extends BaseMapper<OrderCart> {

    /**
     * 查询用户购物车
     *
     * @param uid 用户id
     * @return list
     */
    List<OrderCartVo> queryCart(Long uid);

}
