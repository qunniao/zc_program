package com.zhancheng.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 自主报价分类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_category")
public class Category extends Model<Category> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父类Id没有则为0
     */
    private Long cid;

    /**
     * 类目名称
     */
    private String typeName;

    /**
     * 类目图标地址
     */
    private String cover;

    /**
     * 一级类目,二级类目,三级类目
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;
    @JSONField(serialize = false)
    @TableLogic
    private Boolean isDeleted;
    @JSONField(serialize = false)
    private LocalDateTime gmtCreate;
    @JSONField(serialize = false)
    private LocalDateTime gmtModified;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Module> moduleList;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
