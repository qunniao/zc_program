package com.zhancheng.core.entity;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_order_info")
public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "oid", type = IdType.AUTO)
    private Long oid;

    /**
     * 店铺id
     */
    private Long sid;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;

    /**
     * 实际付款
     */
    private BigDecimal payMoney;

    /**
     * 支付方式
     */
    private Integer payWay;

    /**
     * 联系人
     */
    private String contactName;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 收货地址
     */
    private String contactAddress;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 支付平台  0为余额支付，1为支付宝，2为微信
     */
    private Integer payPlatform;

    /**
     * 第三方支付单号
     */
    private String payNumber;

    /**
     * 配送方式
     */
    private String sendWay;

    /**
     * 运费
     */
    private BigDecimal freight;

    /**
     * 订单状态, -1 已关闭，0代付款，1服务中，3待评价
     */
    private Integer orderState;

    @TableLogic
    @JSONField(serialize = false)
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.oid;
    }

}
