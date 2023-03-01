package com.zhi.mymall.service;

import com.zhi.mymall.beans.ProductParams;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品参数  服务类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
public interface ProductParamsService extends IService<ProductParams> {
ProductParams getById(String s);
}

