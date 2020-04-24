package com.zhancheng.core.dao;

import com.zhancheng.core.entity.OrderPayHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * <p>
 * 支付记录表 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Repository
public interface OrderPayHistoryMapper extends BaseMapper<OrderPayHistory> {


    /**
     * 获取线上交易额(已经支付的金额)
     *
     * @return
     */
    @Select(" SELECT sum(pay_money) FROM zc_order_pay_history")
    BigDecimal getGMV();
}
