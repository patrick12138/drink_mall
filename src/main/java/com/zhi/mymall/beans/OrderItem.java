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
 * 订单项/快照
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "OrderItem对象", description = "订单项/快照 ")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单项ID")
    @TableId(value = "item_id", type = IdType.ASSIGN_ID)
    private String itemId;

    @ApiModelProperty(value = "订单ID")
    private String orderId;

    @ApiModelProperty(value = "商品ID")
    private String productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品图片")
    private String productImg;

    @ApiModelProperty(value = "skuID")
    private String skuId;

    @ApiModelProperty(value = "sku名称")
    private String skuName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "购买数量")
    private Integer buyCounts;

    @ApiModelProperty(value = "商品总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "加入购物车时间")
    private Date basketDate;

    @ApiModelProperty(value = "购买时间")
    private Date buyTime;

    @ApiModelProperty(value = "评论状态： 0 未评价  1 已评价")
    private Integer isComment;


}
