package com.zhi.mymall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TimeImgVO对象", description="历史浏览VO")
public class TimeImgVO {
    @ApiModelProperty(value = "商品名称 商品名称")
    private String productName;
    @ApiModelProperty(value = "商品主键id")
    private String productId;
    @ApiModelProperty(value = "分类外键id 分类id")
    private Integer categoryId;
    @ApiModelProperty(value = "销量 累计销售")
    private Integer soldNum;
    @ApiModelProperty(value = "商品内容 商品内容")
    private String content;
    @ApiModelProperty(value = "图片地址 图片地址")
    private String url;
    @ApiModelProperty(value = "时间 时间")
    private String time;
}
