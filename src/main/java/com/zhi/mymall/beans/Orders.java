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
 * 订单
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Orders对象", description = "订单 ")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID 同时也是订单编号")
    @TableId(value = "order_id", type = IdType.ASSIGN_UUID)
    private String orderId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "产品名称（多个产品用,隔开）")
    private String untitled;

    @ApiModelProperty(value = "收货人快照")
    private String receiverName;

    @ApiModelProperty(value = "收货人手机号快照")
    private String receiverMobile;

    @ApiModelProperty(value = "收货地址快照")
    private String receiverAddress;

    @ApiModelProperty(value = "订单总价格")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "实际支付总价格")
    private Integer actualAmount;

    @ApiModelProperty(value = "支付方式 1:微信 2:支付宝")
    private Integer payType;

    @ApiModelProperty(value = "订单备注")
    private String orderRemark;

    @ApiModelProperty(value = "订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:已完成 6:已关闭")
    private String status;

    @ApiModelProperty(value = "配送方式")
    private String deliveryType;

    @ApiModelProperty(value = "物流单号")
    private String deliveryFlowId;

    @ApiModelProperty(value = "订单运费 默认可以为零，代表包邮")
    private BigDecimal orderFreight;

    @ApiModelProperty(value = "逻辑删除状态 1: 删除 0:未删除")
    private Integer deleteStatus;

    @ApiModelProperty(value = "创建时间（成交时间）")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "付款时间")
    private Date payTime;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "完成时间")
    private Date flishTime;

    @ApiModelProperty(value = "取消时间")
    private Date cancelTime;

    @ApiModelProperty(value = "订单关闭类型1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易")
    private Integer closeType;


}
