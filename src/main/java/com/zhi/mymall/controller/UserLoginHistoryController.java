package com.zhi.mymall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhi.mymall.beans.UserLoginHistory;
import com.zhi.mymall.service.UserLoginHistoryService;
import com.zhi.mymall.utils.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 登录历史表  前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/retaildemo/user-login-history")
@Api(tags = "IP获取")
public class UserLoginHistoryController {
    @Resource
    private UserLoginHistoryService userLoginHistoryService;
    @GetMapping("/getIp")
    @ApiOperation("获取客户IP")
    public String test(HttpServletRequest request){

        //获取IP地址
        String ipAddress = IpUtil.getIpAddr(request);

        return ipAddress;
    }
    @PostMapping("/insert")
    @ApiOperation("记录登录历史")
    public boolean insert(@RequestBody UserLoginHistory userLoginHistory){
        return userLoginHistoryService.saveOrUpdate(userLoginHistory);
    }
    @GetMapping("/getById")
    @ApiOperation("查询用户登录历史")
    public List<UserLoginHistory> getbyid(@RequestParam String id){
        QueryWrapper<UserLoginHistory> userLoginHistoryQueryWrapper = new QueryWrapper<>();
        userLoginHistoryQueryWrapper.eq("user_id",id);
        return userLoginHistoryService.list(userLoginHistoryQueryWrapper);
    }
}

