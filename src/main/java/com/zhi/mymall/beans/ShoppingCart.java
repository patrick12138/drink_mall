package com.zhi.mymall.beans;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShoppingCart对象", description = "购物车 ")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

    @ApiModelProperty(value = "商品ID")
    private String productId;

    @ApiModelProperty(value = "skuID")
    private String skuId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "购物车商品数量")
    private String cartNum;

    @ApiModelProperty(value = "添加购物车时间")
    private String cartTime;

    @ApiModelProperty(value = "添加购物车时商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "选择的套餐的属性")
    private String skuProps;


}
