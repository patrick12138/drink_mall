package com.zhi.mymall.service;

import com.zhi.mymall.beans.UserAddr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户地址  服务类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
public interface UserAddrService extends IService<UserAddr> {
    List<UserAddr> getAddrById(String userId);
}
