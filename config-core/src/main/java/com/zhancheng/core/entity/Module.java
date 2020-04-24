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
 * 模块表
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_module")
public class Module extends Model<Module> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 外键,关联category
     */
    private Long cid;
    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 工时
     */
    private Integer labor;
    @TableLogic
    @JSONField(serialize = false)
    private Boolean isDeleted;
    @JSONField(serialize = false)
    private LocalDateTime gmtCreate;
    @JSONField(serialize = false)
    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
