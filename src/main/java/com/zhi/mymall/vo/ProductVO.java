package com.zhi.mymall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Product对象", description = "商品 商品信息相关表：分类表，商品图片表")
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品主键id")
    private String productId;

    @ApiModelProperty(value = "商品名称 商品名称")
    private String productName;

    @ApiModelProperty(value = "分类外键id 分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "一级分类外键id 一级分类id，用于优化查询")
    private Integer rootCategoryId;

    @ApiModelProperty(value = "销量 累计销售")
    private Integer soldNum;

    @ApiModelProperty(value = "默认是1，表示正常状态, -1表示删除, 0下架 默认是1，表示正常状态, -1表示删除, 0下架")
    private Integer productStatus;

    @ApiModelProperty(value = "商品内容 商品内容")
    private String content;

    @ApiModelProperty(value = "图片主键")
    private String id;

    @ApiModelProperty(value = "商品外键id 商品外键id")
    private String itemId;

    @ApiModelProperty(value = "图片地址 图片地址")
    private String url;

    @ApiModelProperty(value = "顺序 图片顺序，从小到大")
    private Integer sort;

    @ApiModelProperty(value = "是否主图 是否主图，1：是，0：否")
    private Integer isMain;
}
