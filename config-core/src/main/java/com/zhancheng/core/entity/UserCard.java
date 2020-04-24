package com.zhancheng.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户名片
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_user_card")
public class UserCard extends Model<UserCard> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;

    private Long uid;
    /**
     * 名称
     */
    @NotNull
    @ApiModelProperty(value = "姓名", name = "name", example = "钱白白")
    private String name;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称", name = "companyName", example = "阿里云科技")
    private String companyName;

    /**
     * 公司职位
     */
    @ApiModelProperty(value = "公司职位", name = "companyPosition", example = "编辑")
    private String companyPosition;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", name = "cover", example = "http://imag/1.img")
    private String cover;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", name = "phone", example = "15665651240")
    private String phone;

    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号", name = "wechat", example = "15665651240")
    private String wechat;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱", name = "email", example = "xqianbaibai@dingtalk.com")
    private String email;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", name = "address", example = "浙江市杭州市余杭区")
    private String address;

    private Double addressLongitude;

    private Double addressLatitude;

    /**
     * 个人简介
     */
    @ApiModelProperty(value = "个人简介", name = "introText", example = "一个普通人的个人的简介")
    private String introText;

    /**
     * 语音介绍
     */
    private String introYuyin;

    /**
     * 家乡
     */
    @ApiModelProperty(value = "家乡", name = "hometown", example = "安徽省芜湖市花津区")
    private String hometown;

    /**
     * 学校名称
     */
    @ApiModelProperty(value = "学校名称", name = "schoolName", example = "安徽师范大学")
    private String schoolName;

    /**
     * 学校学习时间，比如：2005年-2008年
     */
    @ApiModelProperty(value = "学校学习时间", name = "schoolTime", example = "2005年-2008年")
    private String schoolTime;

    /**
     * 学校专业
     */
    @ApiModelProperty(value = "学校专业", name = "schoolMaster", example = "计算机科学与技术")
    private String schoolMaster;

    /**
     * 学校的学历，比如：大专
     */
    @ApiModelProperty(value = "学校的学历", name = "schoolXueli", example = "本科")
    private String schoolXueli;

    /**
     * 是否推荐
     */
    private Boolean isRecommend;

    @TableLogic
    @JSONField(serialize = false)
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
