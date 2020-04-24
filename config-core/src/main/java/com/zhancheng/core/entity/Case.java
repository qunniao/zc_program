package com.zhancheng.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhancheng.core.dto.JsonDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 案例表
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_case")
public class Case extends Model<Case> {

    private static final long serialVersionUID = 1L;

    /**
     * 案例ID
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;

    /**
     * 店铺id
     */
    private Long sid;

    /**
     * 案例类型id
     */
    private Long tid;

    /**
     * 案例名称
     */
    private String name;

    /**
     * 案例图片地址
     */
    private String cover;

    /**
     * 价格
     */
    private BigDecimal price;
    private String mobileDetail;
    private String content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private JsonDto[] attrs;

    /**
     * 详细内容
     */
    @TableField(value = "is_recommend")
    private Boolean recommend;
    @TableLogic
    @JSONField(serialize = false)
    private Boolean isDeleted;
    @JSONField(serialize = false)
    private Date gmtCreate;
    @JSONField(serialize = false)
    private Date gmtModified;

    @TableField(exist = false)
    private List<CaseAttr> caseAttrs;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private CaseLabel caseLabels;

    @Override
    protected Serializable pkVal() {
        return this.cid;
    }
}
