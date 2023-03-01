package com.zhi.mymall.mapper;

import com.zhi.mymall.beans.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品分类 Mapper 接口
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
