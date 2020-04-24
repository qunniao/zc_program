package com.zhancheng.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 产品类目
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_product_type")
public class ProductType extends Model<ProductType> {

    private static final long serialVersionUID = 1L;

    /**
     * 类目id
     */
    @TableId(value = "tid", type = IdType.AUTO)
    private Long tid;

    /**
     * 类目父亲id
     */
    private Long pid;

    /**
     * 类目名称
     */
    private String typeName;

    /**
     * 上级类目名称
     */
    @TableField(exist = false)
    private String superiorTypeName;

    /**
     * 图标地址
     */
    private String cover;

    /**
     * 排序
     */
    private Integer sort;

    @TableLogic
    @JSONField(serialize = false)
    private Boolean isDeleted;
    @JSONField(serialize = false)
    private LocalDateTime gmtCreate;
    @JSONField(serialize = false)
    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.tid;
    }

}
