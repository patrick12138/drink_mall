package com.zhi.mymall.service.impl;

import com.zhi.mymall.beans.UserLoginHistory;
import com.zhi.mymall.mapper.UserLoginHistoryMapper;
import com.zhi.mymall.service.UserLoginHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录历史表  服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Service
public class UserLoginHistoryServiceImpl extends ServiceImpl<UserLoginHistoryMapper, UserLoginHistory> implements UserLoginHistoryService {

}
