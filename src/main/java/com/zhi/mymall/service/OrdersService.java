package com.zhi.mymall.service;

import com.zhi.mymall.beans.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单  服务类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
public interface OrdersService extends IService<Orders> {
    String addOrder(String cartId, Orders order);

    String queryOrder(String orderNo);

    void checkOrderStatus(String orderNo);

    String tradeCreate(String orderId);
}
