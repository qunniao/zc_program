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
 * 案例属性名称和值
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_case_attr")
public class CaseAttr extends Model<CaseAttr> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;

    private Long cid;

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 属性值
     */
    private String attrValue;
    @JSONField(serialize = false)
    @TableLogic
    private Boolean isDeleted;
    @JSONField(serialize = false)
    private LocalDateTime gmtCreate;
    @JSONField(serialize = false)
    private LocalDateTime gmtModified;

    @Override
    protected Serializable pkVal() {
        return this.aid;
    }

}
