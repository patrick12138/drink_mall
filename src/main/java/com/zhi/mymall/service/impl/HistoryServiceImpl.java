package com.zhi.mymall.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.mymall.beans.History;
import com.zhi.mymall.mapper.HistoryMapper;
import com.zhi.mymall.service.HistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.mymall.vo.TimeImgVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-05-07
 */
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {
    @Resource
    private HistoryMapper historyMapper;
    @Override
    public Page<TimeImgVO> history(Integer id, Page<TimeImgVO> page) {
        return historyMapper.history(id,page);
    }
}
