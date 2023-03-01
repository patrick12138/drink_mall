package com.zhi.mymall.service;

import com.zhi.mymall.beans.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhi.mymall.vo.ResultVO;

/**
 * <p>
 * 用户  服务类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
public interface UsersService extends IService<Users> {

    ResultVO userResgit(String name, String pwd);

    ResultVO checkLogin(String name, String pwd);

    Users findByName(String name);
}
