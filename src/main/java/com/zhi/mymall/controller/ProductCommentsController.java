package com.zhi.mymall.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.mymall.beans.ProductComments;
import com.zhi.mymall.service.ProductCommentsService;
import com.zhi.mymall.vo.ProductCommentsVO;
import com.zhi.mymall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 商品评价  前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/retaildemo/product-comments")
@Api(tags = "商品评价")
public class ProductCommentsController {
    @Resource
    private ProductCommentsService productCommentsService;
    @Value("${files.upload.path}")
    private String fileUploadPath;
    @GetMapping("/msg/{id}")
    @ApiOperation("根据商品id查询商品评论和评价用户的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name="current",value = "当前页码",required = true),
            @ApiImplicitParam(dataType = "int",name="size",value = "每页条数",required = true)
    })
    public IPage<ProductCommentsVO> getCommentsById(@PathVariable("id") String id, int current, int size){
        Page<ProductCommentsVO> productCommentsVOPage = productCommentsService.showComments(id, new Page<>(current, size));
        return productCommentsVOPage;
    }
    @GetMapping("/user/{id}")
    @ApiOperation("根据用户id查询商品评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name="current",value = "当前页码",required = true),
            @ApiImplicitParam(dataType = "int",name="size",value = "每页条数",required = true)
    })
    public Page<ProductComments> getByUserId(@PathVariable("id") String id, int current, int size){
        QueryWrapper<ProductComments> productCommentsQueryWrapper = new QueryWrapper<>();
        productCommentsQueryWrapper.eq("user_id",id);
        Page<ProductComments> productCommentsPage = new Page<>(current,size);
        Page<ProductComments> page = productCommentsService.page(productCommentsPage, productCommentsQueryWrapper);

        return  page;
    }
    @PostMapping("/upload")
    @ApiOperation("评论图片上传到服务器")
    public String upload(@RequestParam MultipartFile file)throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        String uuid = IdUtil.fastSimpleUUID();
        String s=fileUploadPath + uuid + StrUtil.DOT + type;
        File file2 = new File(s);
        file.transferTo(file2);
        String b=uuid + StrUtil.DOT + type;
        return b;
    }
    @PostMapping("/makeComment")
    @ApiOperation("发表评论")
    public ResultVO makeComment(@RequestBody ProductComments productComments){
        if(productCommentsService.save(productComments))return new ResultVO(10000,"发表成功",null);
        else return new ResultVO(10001,"发表评论失败",null);
    }

}

