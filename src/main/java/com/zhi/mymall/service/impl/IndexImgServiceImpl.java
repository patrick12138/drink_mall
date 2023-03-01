package com.zhi.mymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhi.mymall.beans.IndexImg;
import com.zhi.mymall.mapper.IndexImgMapper;
import com.zhi.mymall.service.IndexImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 轮播图  服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Service
public class IndexImgServiceImpl extends ServiceImpl<IndexImgMapper, IndexImg> implements IndexImgService {
    @Resource
    IndexImgMapper indexImgMapper;

    @Override
    public List<IndexImg> indexImg(){
        QueryWrapper<IndexImg> wrapper=new QueryWrapper<>();
        wrapper.eq("status","1");
        wrapper.orderByAsc("seq");
        List<IndexImg> indexImgs = indexImgMapper.selectList(wrapper);
        return indexImgs;
    }
}
