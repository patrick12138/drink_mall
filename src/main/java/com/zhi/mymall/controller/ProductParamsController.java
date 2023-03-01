package com.zhi.mymall.controller;


import com.zhi.mymall.beans.ProductParams;
import com.zhi.mymall.service.ProductParamsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 商品参数  前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/retaildemo/product-params")
@Api(tags = "商品参数")
public class ProductParamsController {
    @Resource
    private ProductParamsService productParamsService;

    @GetMapping("params/{id}")
    @ApiOperation("通过商品ID查询商品参数")
    public ProductParams getParams(@PathVariable("id") String id) {
        ProductParams byId = productParamsService.getById(id);
        return byId;
    }

    @PostMapping("/addParams")
    @ApiOperation("添加商品参数")
    public boolean addparams(@RequestBody ProductParams productParams) {
        return productParamsService.save(productParams);
    }
}

