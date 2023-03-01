package com.zhi.mymall.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "购物车Product对象", description = "商品图片表，购物车表，sku表，商品表")
public class ShoppingCartVO {
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
    private Date cartTime;

    @ApiModelProperty(value = "添加购物车时商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "选择的套餐的属性")
    private String skuProps;

    @ApiModelProperty(value = "商品名称 商品名称")
    private String productName;

    @ApiModelProperty(value = "原价")
    private Integer originalPrice;

    @ApiModelProperty(value = "销售价格")
    private Integer sellPrice;

    @ApiModelProperty(value = "sku名称")
    private String skuName;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "图片地址 图片地址")
    private String url;
}
