package com.zhi.mymall.controller;


import com.zhi.mymall.beans.ShoppingCart;
import com.zhi.mymall.service.ShoppingCartService;
import com.zhi.mymall.vo.ResultVO;
import com.zhi.mymall.vo.ShoppingCartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 购物车  前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/retaildemo/shopping-cart")
@Api(tags = "购物车管理")
public class ShoppingCartController {
    @Resource
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public ResultVO addShoppingCart(@RequestBody ShoppingCart cart, @RequestHeader("token") String token) {
        ResultVO resultVO = shoppingCartService.addShoppingCart(cart);
        return resultVO;
    }

    @GetMapping("/list/{id}")
    @ApiOperation("通过用户ID查询购物车列表")
    public List<ShoppingCartVO> cartList(@PathVariable("id") int id, @RequestHeader("token") String token) {
        return shoppingCartService.ShoppingCartList(id);
    }

    @GetMapping("/update/{id}/{cartNum}")
    @ApiOperation("通过CartId修改商品数量")
    public ResultVO updateCartNum(@PathVariable("id") int id, @PathVariable("cartNum") String num, @RequestHeader("token") String token) {
        return shoppingCartService.updateCartNum(num, id);
    }

    @PostMapping("/listByCartId")
    @ApiOperation("通过多个CartId返回VO集合")
    public List<ShoppingCartVO> getListByCartId(@RequestBody List<Integer> cartId, @RequestHeader("token") String token) {
        return shoppingCartService.ShoppingCartByCartId(cartId);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除一个购物车记录")
    public boolean delete(@PathVariable("id") int id, @RequestHeader("token") String token) {
        return shoppingCartService.removeById(id);
    }

    @PostMapping("/ids")
    @ApiOperation("批量删除")
    public ResultVO batchDelete(@RequestBody List<Integer> ids, @RequestHeader("token") String token) { // [1,2,3]
        if (shoppingCartService.removeByIds(ids))
            return new ResultVO(10000, "删除成功", null);
        else return new ResultVO(10001, "删除失败", null);
    }

}

