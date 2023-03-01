package com.zhi.mymall.service;

import com.zhi.mymall.beans.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhi.mymall.vo.ResultVO;
import com.zhi.mymall.vo.ShoppingCartVO;

import java.util.List;

/**
 * <p>
 * 购物车  服务类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
public interface ShoppingCartService extends IService<ShoppingCart> {
    ResultVO addShoppingCart(ShoppingCart cart);

    List<ShoppingCartVO> ShoppingCartList(int userId);

    ResultVO updateCartNum(String s, int i);

    List<ShoppingCartVO> ShoppingCartByCartId(List<Integer> s);
}
