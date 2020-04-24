package com.zhancheng.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_order_cart")
public class OrderCart extends Model<OrderCart> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;
    private Long sid;

    private Long uid;
    @ApiModelProperty(value = "库存Id", name = "skuId", example = "1")
    private Long skuId;

    private Long spuId;
    private String productName;

    @ApiModelProperty(value = "加购的商品数量(负数就是减少)", name = "productNum", example = "1")
    private Integer productNum;

    private BigDecimal productPrice;
    @TableLogic
    @JSONField(serialize = false)
    private Boolean isDeleted;
    @JSONField(serialize = false)
    private LocalDateTime gmtCreate;
    @JSONField(serialize = false)
    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.cid;
    }

}
