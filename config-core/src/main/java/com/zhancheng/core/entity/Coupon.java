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
 * 商家发布的优惠券
 * </p>
 *
 * @author tangchao
 * @since 2019-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_coupon")
public class Coupon extends Model<Coupon> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;

    /**
     * 店铺id，是否限制为哪个店铺能用
     */
    private Long sid;

    /**
     * 产品id，是否限制为哪个产品能用，0不限制
     */
    private Long pid;

    /**
     * 产品分类id，限制哪个分类能用，0为全场通用
     */
    private Long tid;

    /**
     * 优惠券金额
     */
    private BigDecimal money;

    /**
     * 满多少可用
     */
    private BigDecimal minMoney;

    /**
     * 限制使用开始日期
     */
    private LocalDateTime useDateStart;

    /**
     * 限制使用结束日期
     */
    private LocalDateTime useDateEnd;

    /**
     * 限制开始时间点
     */
    private LocalDateTime useTimeStart;

    /**
     * 限制结束时间点
     */
    private LocalDateTime useTimeEnd;

    /**
     * 是否店铺红包,0不是，1是
     */
    private Boolean isStore;

    /**
     * 是否限制用户本人使用，0不限制，1限制（收货联系电话要和注册手机号一致）
     */
    private Boolean isOneself;
    @JSONField(serialize = false)
    @TableLogic
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
