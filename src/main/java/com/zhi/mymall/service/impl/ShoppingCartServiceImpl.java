package com.zhi.mymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhi.mymall.beans.ShoppingCart;
import com.zhi.mymall.mapper.ShoppingCartMapper;
import com.zhi.mymall.service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.mymall.vo.ResultVO;
import com.zhi.mymall.vo.ShoppingCartVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 购物车  服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public ResultVO addShoppingCart(ShoppingCart cart) {
        int i = shoppingCartMapper.insert(cart);
        QueryWrapper<ShoppingCart> shoppingCartQueryWrapper = new QueryWrapper<>();
        shoppingCartQueryWrapper.orderByDesc("cart_id");
        shoppingCartQueryWrapper.last("limit 1");
        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(shoppingCartQueryWrapper);
        if (i > 0) {
            return new ResultVO(10000, "添加购物车成功", shoppingCart.getCartId());
        } else {
            return new ResultVO(10001, "添加购物车失败", null);
        }

    }

    @Override
    public List<ShoppingCartVO> ShoppingCartList(int userId) {
        return shoppingCartMapper.getByUserId(userId);
    }

    @Override
    public ResultVO updateCartNum(String s, int i) {
        ShoppingCart shoppingCart = new ShoppingCart();
        QueryWrapper<ShoppingCart> shoppingCartQueryWrapper = new QueryWrapper<>();
        shoppingCartQueryWrapper.eq("cart_id", i);
        shoppingCart.setCartNum(s);
        int update = shoppingCartMapper.update(shoppingCart, shoppingCartQueryWrapper);
        if (update > 0) {
            return new ResultVO(10000, "成功", null);
        } else {
            return new ResultVO(10001, "失败", null);
        }
    }

    @Override
    public List<ShoppingCartVO> ShoppingCartByCartId(List<Integer> s) {
        ArrayList<ShoppingCartVO> shoppingCartVOS = new ArrayList<>();
        for (int i : s) {
            ShoppingCartVO byCartId = shoppingCartMapper.getByCartId(String.valueOf(i));
            shoppingCartVOS.add(byCartId);
        }
        return shoppingCartVOS;
    }
}
