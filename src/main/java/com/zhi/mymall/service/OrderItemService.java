package com.zhi.mymall.service;

import com.zhi.mymall.beans.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhi.mymall.vo.ResultVO;

/**
 * <p>
 * 订单项/快照  服务类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
public interface OrderItemService extends IService<OrderItem> {
    ResultVO getByOrderId(String s);
    ResultVO getListByUserId(String s);
    boolean updateComment(String id,int status);
}
