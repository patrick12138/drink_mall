package com.zhi.mymall.beans;

import java.math.BigDecimal;
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
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductSku对象", description = "商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计")
public class ProductSku implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "sku编号")
    @TableId(value = "sku_id", type = IdType.ASSIGN_ID)
    private String skuId;

    @ApiModelProperty(value = "商品id")

    private String productId;

    @ApiModelProperty(value = "sku名称")
    private String skuName;

    @ApiModelProperty(value = "sku图片")
    private String skuImg;

    @ApiModelProperty(value = "属性组合（格式是p1:v1;p2:v2）")
    private String untitled;

    @ApiModelProperty(value = "原价")
    private Integer originalPrice;

    @ApiModelProperty(value = "销售价格")
    private Integer sellPrice;

    @ApiModelProperty(value = "折扣力度")
    private BigDecimal discounts;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "sku状态(1启用，0禁用，-1删除)")
    private Integer status;


}
