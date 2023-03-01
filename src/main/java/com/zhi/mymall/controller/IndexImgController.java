package com.zhi.mymall.controller;


import com.zhi.mymall.service.IndexImgService;
import com.zhi.mymall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 轮播图  前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/retaildemo/index-img")
@Api(tags = "轮播图")
public class IndexImgController {
    @Resource
    IndexImgService indexImgService;

    @GetMapping("/img")
    @ApiOperation("返回轮播图信息")
    public ResultVO img() {
        return new ResultVO(10000, "成功", indexImgService.indexImg());
    }
}

