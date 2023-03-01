package com.zhi.mymall.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhi.mymall.beans.Users;
import com.zhi.mymall.service.UsersService;
import com.zhi.mymall.vo.PriceImg;
import com.zhi.mymall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 用户  前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/retaildemo/user")
@Api(tags = "用户管理")
public class UsersController {
    @Autowired
    UsersService usersService;

    @GetMapping("/one/{id}")
    @ApiOperation("通过ID查询用户信息")
    public Users select(@PathVariable("id") String id) {
        return this.usersService.findByName(id);
    }

    @PostMapping
    public boolean save(@RequestBody Users users) {
        return usersService.saveOrUpdate(users);
    }

    // 查询所有数据
    @GetMapping
    public List<Users> findAll() {
        return usersService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return usersService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return usersService.removeByIds(ids);
    }

    @GetMapping("/page")
    public IPage<Users> findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String username,
                                 @RequestParam(defaultValue = "") String user_email,
                                 @RequestParam(defaultValue = "") String nickname) {
        IPage<Users> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if (!"".equals(user_email)) {
            queryWrapper.like("user_email", user_email);
        }
        if (!"".equals(nickname)) {
            queryWrapper.like("nickname", nickname);
        }
        queryWrapper.orderByDesc("user_id");
        return usersService.page(page, queryWrapper);
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Users> list = usersService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
/*        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("nickname", "昵称");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("avatarUrl", "头像");*/

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    /**
     * excel 导入
     *
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Users> list = reader.readAll(Users.class);
        usersService.saveBatch(list);
        return true;
    }

    @GetMapping("/login")
    public ResultVO login(@RequestParam("username") String name,
                          @RequestParam(value = "password") String pwd) {
        ResultVO resultVO = usersService.checkLogin(name, pwd);
        return resultVO;
    }

    @PostMapping("/register")
    public ResultVO register(@RequestBody Users users) {
        ResultVO resultVO = usersService.userResgit(users.getUsername(), users.getPassword());
        return resultVO;
    }

    @GetMapping("/list")
    public IPage<Users> listProduct(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return new Page<Users>(pageNum, pageSize).setRecords(usersService.list(null));
    }

    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestParam Integer userId){
        return usersService.removeById(userId);
    }
}

