package com.zhi.mymall.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 商品评价
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductCommentsVO对象", description="商品评价与用户信息 ")
public class ProductCommentsVO {
    @ApiModelProperty(value = "主键id 用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户名 用户名")
    private String username;
    @ApiModelProperty(value = "昵称 昵称")
    private String nickname;
    @ApiModelProperty(value = "头像 头像")
    private String userImg;
    @ApiModelProperty(value = "ID")
    private String commId;

    @ApiModelProperty(value = "商品id")
    private String productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "订单项(商品快照)ID 可为空")
    private String orderItemId;

    @ApiModelProperty(value = "是否匿名（1:是，0:否）")
    private Integer isAnonymous;

    @ApiModelProperty(value = "评价类型（1好评，0中评，-1差评）")
    private Integer commType;

    @ApiModelProperty(value = "评价等级 1：好评 2：中评 3：差评")
    private Integer commLevel;

    @ApiModelProperty(value = "评价内容")
    private String commContent;

    @ApiModelProperty(value = "评价晒图(JSON {img1:url1,img2:url2}  )")
    private String commImgs;

    @ApiModelProperty(value = "评价时间 可为空")
    private Date sepcTime;

    @ApiModelProperty(value = "是否回复（0:未回复，1:已回复）")
    private Integer replyStatus;

    @ApiModelProperty(value = "回复内容")
    private String replyContent;

    @ApiModelProperty(value = "回复时间")
    private Date replyTime;

    @ApiModelProperty(value = "是否显示（1:是，0:否）")
    private Integer isShow;
}
