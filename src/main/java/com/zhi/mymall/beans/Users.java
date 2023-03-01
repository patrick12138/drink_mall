package com.zhi.mymall.beans;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Users对象", description = "用户 ")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id 用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户名 用户名")
    private String username;

    @ApiModelProperty(value = "密码 密码")
    private String password;

    @ApiModelProperty(value = "昵称 昵称")
    private String nickname;

    @ApiModelProperty(value = "真实姓名 真实姓名")
    private String realname;

    @ApiModelProperty(value = "头像 头像")
    private String userImg;

    @ApiModelProperty(value = "手机号 手机号")
    private String userMobile;

    @ApiModelProperty(value = "邮箱地址 邮箱地址")
    private String userEmail;

    @ApiModelProperty(value = "性别 M(男) or F(女)")
    private String userSex;

    @ApiModelProperty(value = "生日 生日")
    private Date userBirth;

    @ApiModelProperty(value = "注册时间 创建时间")
    private Date userRegtime;

    @ApiModelProperty(value = "更新时间 更新时间")
    private Date userModtime;


}
