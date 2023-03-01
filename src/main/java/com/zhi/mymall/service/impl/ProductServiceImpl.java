package com.zhi.mymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.mymall.beans.Category;
import com.zhi.mymall.beans.Product;
import com.zhi.mymall.beans.ProductImg;
import com.zhi.mymall.beans.ProductSku;
import com.zhi.mymall.mapper.CategoryMapper;
import com.zhi.mymall.mapper.ProductImgMapper;
import com.zhi.mymall.mapper.ProductMapper;
import com.zhi.mymall.mapper.ProductSkuMapper;
import com.zhi.mymall.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.mymall.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Resource
    ProductMapper productMapper;
    @Resource
    ProductImgMapper productImgMapper;
    @Resource
    CategoryMapper categoryMapper;
    @Resource
    ProductSkuMapper productSkuMapper;

    @Override
    public List<ProductVO> recommend() {
        QueryWrapper<Product> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.orderByDesc("create_time");
        objectQueryWrapper.last("limit 4");
        List<Product> products = productMapper.selectList(objectQueryWrapper);
        ArrayList<ProductVO> objects = new ArrayList<>();
        for (Product product : products) {
            QueryWrapper<ProductImg> productImgQueryWrapper = new QueryWrapper<>();
            productImgQueryWrapper.eq("item_id", product.getProductId()).eq("is_main", 1);
            ProductImg productImg = productImgMapper.selectOne(productImgQueryWrapper);
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            BeanUtils.copyProperties(productImg, productVO);
            objects.add(productVO);
        }
        return objects;
    }

    @Override
    public ResultVO getlist(String id) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", id);
        List<Product> product = productMapper.selectList(queryWrapper);
        if (product.size() > 0) {
            QueryWrapper<ProductSku> productSkuQueryWrapper = new QueryWrapper<>();
            productSkuQueryWrapper.eq("product_id", id);
            List<ProductSku> productSku = productSkuMapper.selectList(productSkuQueryWrapper);
            QueryWrapper<ProductImg> productImgQueryWrapper = new QueryWrapper<>();
            productImgQueryWrapper.eq("item_id", id);
            productImgQueryWrapper.orderByAsc("sort");
            List<ProductImg> productImg = productImgMapper.selectList(productImgQueryWrapper);
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("product", product.get(0));
            stringObjectHashMap.put("productImgs", productImg);
            stringObjectHashMap.put("productSku", productSku);
            return new ResultVO(10000, "成功", stringObjectHashMap);
        }
        return new ResultVO(10001, "查询商品不存在", null);
    }

    @Override
    public List<CategoryVO> categoryRecommend() {
        //找到一级分类
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_level", "1");
        List<Category> categoriesList = categoryMapper.selectList(categoryQueryWrapper);
        ArrayList<CategoryVO> levelone = new ArrayList<>();
        for (Category category : categoriesList) {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category, categoryVO);
            levelone.add(categoryVO);
        }
        for (CategoryVO categoryVO : levelone) {
            int i = categoryVO.getCategoryId();
            QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
            productQueryWrapper.eq("root_category_id", i);
            productQueryWrapper.orderByDesc("sold_num");
            productQueryWrapper.last("limit 4");
            List<Product> products = productMapper.selectList(productQueryWrapper);
            ArrayList<ProductVO> objects = new ArrayList<>();
            for (Product product : products) {
                QueryWrapper<ProductImg> productImgQueryWrapper = new QueryWrapper<>();
                productImgQueryWrapper.eq("item_id", product.getProductId()).eq("is_main", 1);
                ProductImg productImg = productImgMapper.selectOne(productImgQueryWrapper);
                ProductVO productVO = new ProductVO();
                BeanUtils.copyProperties(product, productVO);
                BeanUtils.copyProperties(productImg, productVO);
                objects.add(productVO);
            }
            categoryVO.setProducts(objects);
        }
        return levelone;
    }

    @Override
    public Page<PriceImg> getByCategoryId(String id, Page<PriceImg> page) {
       /*
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",id);
        List<Product> products = productMapper.selectList(queryWrapper);
        ArrayList<ProductVO> object = new ArrayList<>();
        for (Product product : products) {
            ProductImg productImg = productImgMapper.selectById(product.getProductId());
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            BeanUtils.copyProperties(productImg, productVO);
            object.add(productVO);
        }
        return object;*/
        return page.setRecords(productMapper.getByCategoryId(id, page));
    }

    @Override
    public Page<PriceImg> fuzzyQuery(String id, Page<PriceImg> page) {
        return page.setRecords(productMapper.fuzzyQuery(id, page));
    }

    @Override
    public Page<ProductListVo> listProduct(Page<ProductListVo> page) {
        return page.setRecords(productMapper.listProduct(page));
    }

    @Override
    public ProductListVo getProductList(String id) {
        return productMapper.getProductList(id);
    }

    @Override
    public Boolean updateProduct(ProductListVo productListVo) {
        return productMapper.updateProductListVo(productListVo);
    }
}