package com.zhancheng.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录密码
     */
    @JsonIgnore
    private String pwd;

    /**
     * 登录手机号
     */
    private String phone;

    /**
     * 第三方
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 出生年月日
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private LocalDate birth;

    /**
     * 0为保密，1为男，2为女
     */
    private Integer gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String cover;

    /**
     * 0是管理员用户,1是普通用户
     */
    private String status;

    /**
     * 极光登录用户名
     */
    private String jmsgName;

    /**
     * 极光登录密码
     */
    private String jmsgPwd;

    @TableLogic
    @JSONField(serialize = false)
    private Boolean isDeleted;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtCreate;
    @JSONField(serialize = false)
    private Date gmtModified;

    private Date gmtLogin;


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
