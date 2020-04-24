package com.zhancheng.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 定制功能反馈表
 * </p>
 *
 * @author tangchao
 * @since 2019-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_feelback")
public class Feelback extends Model<Feelback> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 提交内容
     */
    @NotNull
    @ApiModelProperty(value = "提交内容", name = "content", example = "希望添加,修改密码模块")
    private String content;

    /**
     * 联系人
     */
    @NotNull
    @ApiModelProperty(value = "联系人", name = "contacts", example = "李达康")
    private String contacts;

    /**
     * 联系手机号
     */
    @NotNull
    @ApiModelProperty(value = "联系手机号", name = "phone", example = "15895647885")
    private String phone;

    @ApiModelProperty(value = "状态：0：待处理 1：已解决； 2：未解决", name = "state", example = "1")
    private Integer state;

    @TableLogic
    @JSONField(serialize = false)
    private Boolean isDeleted;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
