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
 * 名片浏览,点赞记录表
 * </p>
 *
 * @author tangchao
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_card_history")
public class CardHistory extends Model<CardHistory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名片Id
     */
    private Long userCardId;

    /**
     * 用户Id
     */
    private Long uid;

    /**
     * 浏览次数
     */
    private Integer num;

    @TableLogic
    private Boolean isDeleted;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    /**
     * 0是点赞,1是浏览
     */
    private Integer type;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
