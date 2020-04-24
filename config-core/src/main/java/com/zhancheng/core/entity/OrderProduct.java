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
 * 订单商品表
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_order_product")
public class OrderProduct extends Model<OrderProduct> {

    private static final long serialVersionUID = 1L;

    private Long oid;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 产品销售编号
     */
    private Long skuId;

    /**
     * 产品编号
     */
    private Long spuId;

    /**
     * 店铺id
     */
    private Long sid;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品数量
     */
    private Integer productNum;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    @TableLogic
    private Integer isDeleted;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.oid;
    }

}
