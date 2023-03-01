package com.zhi.mymall.mapper;

import com.zhi.mymall.beans.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单项/快照  Mapper 接口
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
