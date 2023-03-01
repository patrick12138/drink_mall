package com.zhi.mymall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhi.mymall.beans.UserAddr;
import com.zhi.mymall.service.UserAddrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户地址  前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/retaildemo/user-addr")
@Api(tags = "收货地址管理 ")
public class UserAddrController {
    @Resource
    private UserAddrService userAddrService;

    @GetMapping("/addr/{id}")
    @ApiOperation("通过用户ID获取收货地址")
    public List<UserAddr> getAddr(@PathVariable("id") String UserId) {
        return userAddrService.getAddrById(UserId);
    }

    @PostMapping("/addAddr")
    @ApiOperation("添加收货地址")
    public boolean addAddr(@RequestBody UserAddr userAddr) {
        return userAddrService.save(userAddr);
    }

    @GetMapping("/updateCommonAddr")
    @ApiOperation("修改默认地址")
    public boolean updatecommonaddr(@RequestParam String id) {
        QueryWrapper<UserAddr> userAddrQueryWrapper = new QueryWrapper<>();
        userAddrQueryWrapper.eq("common_addr", 1);
        UserAddr userAddr1 = new UserAddr();
        userAddr1.setCommonAddr(0);
        userAddrService.update(userAddr1, userAddrQueryWrapper);
        UserAddr userAddr = new UserAddr();
        userAddr.setCommonAddr(1);
        userAddr.setAddrId(id);
        return userAddrService.updateById(userAddr);
    }

    @DeleteMapping("/deleteOne")
    @ApiOperation("根据ID删除")
    public boolean deleteone(@RequestParam Integer id) {
        return userAddrService.removeById(id);

    }
}

