package com.zhi.mymall.mapper;

import com.zhi.mymall.beans.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhi.mymall.vo.ShoppingCartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 购物车  Mapper 接口
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
    @Select(" select c.cart_id, c.product_id,  c.sku_id, c.user_id, c.cart_num, c.cart_time,\n" +
            "     c.product_price, c.sku_props,  p.product_name, i.url,s.original_price,s.sell_price,s.sku_name,s.stock\n" +
            "    from shopping_cart c\n" +
            "    INNER JOIN product p\n" +
            "    INNER JOIN product_img i\n" +
            "    INNER JOIN product_sku s\n" +
            "    ON c.product_id = p.product_id\n" +
            "    and i.item_id=p.product_id\n" +
            "    and c.sku_id=s.sku_id\n" +
            "    where user_id=#{userId}\n" +
            "    and i.is_main=1")
    List<ShoppingCartVO> getByUserId(@Param("userId") int userId);

    @Select(" select c.cart_id, c.product_id,  c.sku_id, c.user_id, c.cart_num, c.cart_time,\n" +
            "     c.product_price, c.sku_props,  p.product_name, i.url,s.original_price,s.sell_price,s.sku_name,s.stock\n" +
            "    from shopping_cart c\n" +
            "    INNER JOIN product p\n" +
            "    INNER JOIN product_img i\n" +
            "    INNER JOIN product_sku s\n" +
            "    ON c.product_id = p.product_id\n" +
            "    and i.item_id=p.product_id\n" +
            "    and c.sku_id=s.sku_id\n" +
            "    where cart_id=#{cartId}\n" +
            "    and i.is_main=1")
    ShoppingCartVO getByCartId(@Param("cartId") String cartId);

    @Select(" select c.cart_id, c.product_id,  c.sku_id, c.user_id, c.cart_num, c.cart_time,\n" +
            "     c.product_price, c.sku_props,  p.product_name, i.url,s.original_price,s.sell_price,s.sku_name,s.stock\n" +
            "    from shopping_cart c\n" +
            "    INNER JOIN product p\n" +
            "    INNER JOIN product_img i\n" +
            "    INNER JOIN product_sku s\n" +
            "    ON c.product_id = p.product_id\n" +
            "    and i.item_id=p.product_id\n" +
            "    and c.sku_id=s.sku_id\n" +
            "    where cart_id=#{cartId}\n" +
            "    and i.is_main=1")
    ShoppingCartVO getByCartIdOnOrder(@Param("cartId") String cartId);


}
