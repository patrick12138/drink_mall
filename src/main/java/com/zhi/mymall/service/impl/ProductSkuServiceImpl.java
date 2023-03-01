package com.zhi.mymall.service.impl;

import com.zhi.mymall.beans.ProductSku;
import com.zhi.mymall.mapper.ProductSkuMapper;
import com.zhi.mymall.service.ProductSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计 服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {

}
