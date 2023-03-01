package com.zhi.mymall.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Category对象", description = "商品分类")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键 分类id主键")
    @TableId(value = "category_id", type = IdType.ASSIGN_UUID)
    private Integer categoryId;

    @ApiModelProperty(value = "分类名称 分类名称")
    private String categoryName;

    @ApiModelProperty(value = "分类层级 分类得类型， 1:一级大分类 2:二级分类 3:三级小分类")
    private Integer categoryLevel;

    @ApiModelProperty(value = "父层级id 父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级")
    private Integer parentId;

    @ApiModelProperty(value = "图标 logo")
    private String categoryIcon;

    @ApiModelProperty(value = "口号")
    private String categorySlogan;

    @ApiModelProperty(value = "分类图")
    private String categoryPic;

    @ApiModelProperty(value = "背景颜色")
    private String categoryBgColor;

}
