package com.zhi.mymall.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhi.mymall.beans.ProductImg;
import com.zhi.mymall.beans.Users;
import com.zhi.mymall.mapper.UsersMapper;
import com.zhi.mymall.service.ProductImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * <p>
 * 商品图片  前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/retaildemo/product-img")
@Api(tags = "用户头像修改")
public class ProductImgController {
    /**
     * 文件上传接口
     *
     * @param file 前端传递过来的文件
     * @return
     * @throws IOException
     */
    @Value("${files.upload.path}")
    private String fileUploadPath;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private ProductImgService productImgService;

    @PostMapping("/upload/{username}")
    @ApiOperation("头像上传到服务器")
    public String upload(@RequestParam MultipartFile file,
                         @PathVariable String username) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        String uuid = IdUtil.fastSimpleUUID();
        String s = fileUploadPath + uuid + StrUtil.DOT + type;
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("username", username);
        Users users = usersMapper.selectOne(usersQueryWrapper);
        String b = uuid + StrUtil.DOT + type;
        users.setUserImg(b);
        usersMapper.update(users, usersQueryWrapper);
        File file2 = new File(s);
        file.transferTo(file2);
        return "";
    }

    /**
     * 文件下载接口   http://localhost:9090/file/{fileUUID}
     *
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    @ApiOperation("头像从服务器获取")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    @PostMapping("/addProductImg")
    @ApiOperation("给商品添加图片")
    public boolean addproductimg(@RequestBody ProductImg productImg) {
        return productImgService.save(productImg);
    }
}

