package com.zhancheng.core.entity;

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
 * 订单评论表
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_order_product_comment")
public class OrderProductComment extends Model<OrderProductComment> {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 产品销售编号
     */
    private Long skuId;

    /**
     * 产品编号
     */
    private Long spuId;

    /**
     * 评论用户id
     */
    private Long uid;

    /**
     * 点赞数量
     */
    private Integer likeNum;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评论分数（1-5分）
     */
    private Integer score;

    /**
     * 评论图片
     */
    private String imageJson;

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
