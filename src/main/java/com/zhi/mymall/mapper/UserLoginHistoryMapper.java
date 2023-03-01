package com.zhi.mymall.mapper;

import com.zhi.mymall.beans.UserLoginHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 登录历史表  Mapper 接口
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Mapper
public interface UserLoginHistoryMapper extends BaseMapper<UserLoginHistory> {

}
