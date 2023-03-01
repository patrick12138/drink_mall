package com.zhi.mymall.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.mymall.beans.ProductComments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhi.mymall.vo.ProductCommentsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品评价  Mapper 接口
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Mapper
public interface ProductCommentsMapper extends BaseMapper<ProductComments> {
    @Select("select u.username,  u.nickname, u.user_img,\n" +
            "               c.comm_id, c.product_id, c.product_name,\n " +
            "               c.order_item_id, c.user_id, c.is_anonymous,\n" +
            "               c.comm_type, c.comm_level, c.comm_content,\n" +
            "               c.comm_imgs, c.sepc_time, c.reply_status,\n" +
            "               c.reply_content, c.reply_time, c.is_show\n" +
            "        from product_comments c\n" +
            "                 INNER JOIN users u\n" +
            "                            ON u.user_id = c.user_id\n" +
            "        WHERE c.product_id =#{productId}")
List<ProductCommentsVO> getCommentsById(@Param("productId") String productId, Page<ProductCommentsVO> page);
}
