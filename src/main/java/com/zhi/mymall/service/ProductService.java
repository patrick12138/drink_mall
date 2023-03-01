package com.zhi.mymall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.mymall.beans.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhi.mymall.vo.*;

import java.util.List;

/**
 * <p>
 * 商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 服务类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
public interface ProductService extends IService<Product> {
    public List<ProductVO> recommend();

    public ResultVO getlist(String id);

    List<CategoryVO> categoryRecommend();

    Page<PriceImg> getByCategoryId(String id, Page<PriceImg> page);

    Page<PriceImg> fuzzyQuery(String id, Page<PriceImg> page);

    Page<ProductListVo> listProduct(Page<ProductListVo> page);

    ProductListVo getProductList(String id);

    Boolean updateProduct(ProductListVo productListVo);
}
