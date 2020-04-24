package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 自主报价订单
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_order_module")
public class OrderModule extends Model<OrderModule> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 一级分类Id
     */
    private Long pid;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 模块数组,用json表示
     */
    private String moduleJson;

    /**
     * 工期
     */
    private String duration;
    /**
     * 总价格
     */
    private BigDecimal totalPrice;
    /**
     * 功能数量
     */
    private Integer num;

    @TableLogic
    private Boolean isDeleted;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private User user;
    private Category category;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
