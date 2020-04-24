package com.zhancheng.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * 案例标签
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_case_label")
public class CaseLabel extends Model<CaseLabel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bid", type = IdType.AUTO)
    private Long bid;

    /**
     * 上级id
     */
    private Long pid;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 标签名称
     */
    private String cover;
    /**
     * 上级标签名称
     */
    @TableField(exist = false)
    private String superiorLabelName;

    @TableLogic
    @JSONField(serialize = false)
    private Boolean isDeleted;
    @JSONField(serialize = false)
    private LocalDateTime gmtCreate;
    @JSONField(serialize = false)
    private LocalDateTime gmtModified;

    @TableField(exist = false)
    private Set<CaseLabel> caseLabelSet;


    @Override
    protected Serializable pkVal() {
        return this.bid;
    }

}
