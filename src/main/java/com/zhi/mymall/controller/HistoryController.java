package com.zhi.mymall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.mymall.beans.History;
import com.zhi.mymall.service.HistoryService;
import com.zhi.mymall.vo.TimeImgVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-05-07
 */
@RestController
@RequestMapping("/retaildemo/history")
@Api(tags = "历史浏览")
public class HistoryController {
    @Resource
    private HistoryService historyService;

    @PostMapping("/insert")
    @ApiOperation("插入一条历史浏览信息")
    public boolean insert(@RequestBody History history) {
        QueryWrapper<History> historyQueryWrapper = new QueryWrapper<>();
        historyQueryWrapper.eq("user_id", history.getUserId());
        historyQueryWrapper.eq("product_id", history.getProductId());
        History one = historyService.getOne(historyQueryWrapper);
        if (one != null) {
            history.setId(one.getId());
        }

        return historyService.saveOrUpdate(history);
    }

    @GetMapping("/getByUserId")
    @ApiOperation("根据用户ID查询历史信息")
    public Page<TimeImgVO> getbyuserid(@RequestParam Integer userId, @RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize) {
        return historyService.history(userId, new Page<>(pageNum, pageSize));
    }
}

