package com.zhi.mymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhi.mymall.beans.UserAddr;
import com.zhi.mymall.mapper.UserAddrMapper;
import com.zhi.mymall.service.UserAddrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户地址  服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Service
public class UserAddrServiceImpl extends ServiceImpl<UserAddrMapper, UserAddr> implements UserAddrService {
    @Resource
    private UserAddrMapper userAddrMapper;
    @Override
    public List<UserAddr> getAddrById(String UserId) {
        QueryWrapper<UserAddr> userAddrQueryWrapper = new QueryWrapper<>();
        userAddrQueryWrapper.eq("user_id",UserId);
        List<UserAddr> userAddr = userAddrMapper.selectList(userAddrQueryWrapper);
        return userAddr;
    }
}
