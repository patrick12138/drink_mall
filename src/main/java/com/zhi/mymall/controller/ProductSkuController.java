package com.zhi.mymall.controller;


import com.zhi.mymall.beans.ProductSku;
import com.zhi.mymall.service.ProductSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计 前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/retaildemo/product-sku")
@Api(tags = "商品Sku")
public class ProductSkuController {
    @Autowired
    ProductSkuService productSkuService;

    @GetMapping("/SkuGet")
    @ApiOperation("查询所有商品的SKU")
    public List<ProductSku> SkuGet() {
        return this.productSkuService.list();
    }

    @PostMapping("/addSku")
    @ApiOperation("给商品添加一个sku")
    public boolean addsku(@RequestBody ProductSku productSku) {
        return productSkuService.save(productSku);
    }
}

