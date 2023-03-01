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
 * 商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Product对象", description = "商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品主键id")
    @TableId(value = "product_id", type = IdType.ASSIGN_UUID)
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

    @ApiModelProperty(value = "商品内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
