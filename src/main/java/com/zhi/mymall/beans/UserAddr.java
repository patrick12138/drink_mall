package com.zhi.mymall.beans;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户地址
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserAddr对象", description = "用户地址 ")
public class UserAddr implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地址主键id")
    @TableId(value = "addr_id", type = IdType.AUTO)
    private String addrId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "收件人姓名")
    private String receiverName;

    @ApiModelProperty(value = "收件人手机号")
    private String receiverMobile;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String area;

    @ApiModelProperty(value = "详细地址")
    private String addr;

    @ApiModelProperty(value = "邮编")
    private String postCode;

    @ApiModelProperty(value = "状态,1正常，0无效")
    private Integer status;

    @ApiModelProperty(value = "是否默认地址 1是 1:是  0:否")
    private Integer commonAddr;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
