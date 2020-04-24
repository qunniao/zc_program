package com.zhancheng.core.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付记录表
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_order_pay_history")
public class OrderPayHistory extends Model<OrderPayHistory> {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 支付方式
     */
    private Integer payWay;

    /**
     * 订单总额
     */
    private BigDecimal orderMoney;

    /**
     * 支付总额
     */
    private BigDecimal payMoney;

    /**
     * 第三方支付平台参数信息，可使用json方式保存
     */
    private String payJson;

    /**
     * 备注 
     */
    private String remark;

    @TableLogic
    private Boolean isDeleted;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
