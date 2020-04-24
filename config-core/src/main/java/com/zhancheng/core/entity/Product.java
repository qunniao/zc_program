package com.zhancheng.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 产品spu表
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_product")
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pid", type = IdType.AUTO)
    private Long pid;

    /**
     * 店铺id
     */
    @JSONField(serialize = false)
    private Long sid;

    /**
     * 商品类型id
     */
    private Long ptId;
    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品描述
     */
    private String productIntro;

    /**
     * 产品价格
     */
    private BigDecimal price;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 价格单位
     */
    private String priceUnit;

    /**
     * 库存
     */
    private Integer store;

    /**
     * 是否推荐
     */
    private Boolean isRecommend;

    /**
     * 产品状态：0上架中，-1已下架
     */
    private Integer status;

    @TableLogic
    @JSONField(serialize = false)
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @JSONField(serialize = false)
    private LocalDateTime gmtCreate;

    @JSONField(serialize = false)
    private LocalDateTime gmtModified;

    @TableField(exist = false)
    private ProductDetailMobile productDetailMobile;
    @TableField(exist = false)
    private ProductDetailPc productDetailPc;
    @TableField(exist = false)
    private List<ProductImage> productImage;
    @TableField(exist = false)
    private List<ProductParam> productParam;


    @Override
    protected Serializable pkVal() {
        return this.pid;
    }

}
