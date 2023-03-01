package com.zhi.mymall.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.mymall.beans.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhi.mymall.vo.PriceImg;
import com.zhi.mymall.vo.ProductListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 Mapper 接口
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @Select(" select distinct p.product_name,p.product_id,p.sold_num,p.content,p.category_id, i.url,s.sell_price\n" +
            " from product p  INNER JOIN product_img i INNER JOIN product_sku s \n" +
            "            on i.item_id=p.product_id and s.product_id=p.product_id \n" +
            "            where p.category_id=#{categoryId} and i.is_main=1")
    List<PriceImg> getByCategoryId(@Param("categoryId") String categoryId, Page<PriceImg> page);

    @Select(" select distinct p.product_name,p.product_id,p.sold_num,p.content,p.category_id, i.url,s.sell_price\n" +
            " from product p  INNER JOIN product_img i INNER JOIN product_sku s \n" +
            "            on i.item_id=p.product_id and s.product_id=p.product_id \n" +
            "            where p.product_name like concat('%',#{keyword},'%') and i.is_main=1")
    List<PriceImg> fuzzyQuery(@Param("keyword") String keyword, Page<PriceImg> page);

    @Select(" SELECT p.product_id, p.category_id, p.product_name, p.content, p.product_status, p.sold_num, i.url," +
            "    s.sell_price,s.original_price,s.stock\n" +
            "    FROM product p\n" +
            "    INNER JOIN product_img i\n" +
            "    INNER JOIN product_sku s\n" +
            "    ON i.item_id = p.product_id\n" +
            "    AND s.product_id = p.product_id")
    List<ProductListVo> listProduct(Page<ProductListVo> page);

    @Select(" SELECT p.product_id, p.category_id, p.product_name, p.content,i.url," +
            "    s.sell_price,s.original_price,s.stock\n" +
            "    FROM product p\n" +
            "    INNER JOIN product_img i\n" +
            "    INNER JOIN product_sku s\n" +
            "    ON i.item_id=p.product_id\n" +
            "    AND s.product_id = p.product_id" +
            "    WHERE p.product_id=#{id}")
    ProductListVo getProductList(String id);

    @Update(" UPDATE product p" +
            "    INNER JOIN product_img i\n" +
            "    INNER JOIN product_sku s\n" +
            "    SET p.product_id = #{productId},p.category_id = #{categoryId}, " +
            "    p.product_name = #{productName}, p.content = #{content}, i.url = #{url}," +
            "    s.sell_price = #{sellPrice},s.original_price = #{originalPrice},s.stock = #{stock}\n" +
            "    ON i.item_id = p.product_id\n" +
            "    AND s.product_id = p.product_id\n" +
            "    WHERE p.product_id = #{productId}")
    Boolean updateProductListVo(ProductListVo vo);
}
