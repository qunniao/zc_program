package com.zhancheng.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 产品主图和轮播图
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_product_image")
public class ProductImage extends Model<ProductImage> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bid", type = IdType.AUTO)
    private Long bid;

    /**
     * 产品id
     */
    private Long pid;

    /**
     * 图片路径
     */
    private String url;

    /**
     * 排序
     */
    private Boolean sort;

    /**
     * 0为不是，1为主图
     */
    private Boolean isCover;
    /**
     * 0表示图片，1表示视频
     */
    private Boolean flag;

    @TableLogic
    @JSONField(serialize = false)
    private Boolean isDeleted;
    @JSONField(serialize = false)
    private LocalDateTime gmtCreate;
    @JSONField(serialize = false)
    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.bid;
    }

}
