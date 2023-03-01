package com.zhi.mymall.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * p.produc_id, p.category_id, p.product_name, p.content, i.url," +
 * "    s.sell_price,s.untitled,s.sku_name,s.stock
 */
@Data
public class ProductListVo {
    @ApiModelProperty(value = "商品ID")
    @TableId
    private String productId;

    @ApiModelProperty(value = "分类外键id 分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品内容")
    private String content;

    @ApiModelProperty(value = "图片地址 图片地址")
    private String url;

    @ApiModelProperty(value = "销售价格")
    private Integer sellPrice;

    @ApiModelProperty(value = "原价")
    private String originalPrice;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "销量 累计销售")
    private Integer soldNum;

    @ApiModelProperty(value = "默认是1，表示正常状态, -1表示删除, 0下架 默认是1，表示正常状态, -1表示删除, 0下架")
    private Integer productStatus;
}
