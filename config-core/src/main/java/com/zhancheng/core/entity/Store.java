package com.zhancheng.core.entity;

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
 * 店铺
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_store")
public class Store extends Model<Store> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sid", type = IdType.AUTO)
    private Long sid;

    private Long uid;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 店铺联系电话
     */
    private String storePhone;

    /**
     * 工作状态,1为正常，0为已休息
     */
    private Boolean isWork;

    /**
     * 经度
     */
    private Double storeLongitude;

    /**
     * 纬度
     */
    private Double storeLatitude;

    /**
     * 店铺详细地址
     */
    private String storeAddress;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String county;

    /**
     * 店铺头像
     */
    private String storeCover;

    /**
     * 店铺简介
     */
    private String storeIntro;

    @TableLogic
    private Boolean isDeleted;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.sid;
    }

}
