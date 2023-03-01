package com.zhi.mymall.service;

import com.zhi.mymall.beans.IndexImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 轮播图  服务类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
public interface IndexImgService extends IService<IndexImg> {
    public List<IndexImg> indexImg();
}
