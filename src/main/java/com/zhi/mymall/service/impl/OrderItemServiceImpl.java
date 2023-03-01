package com.zhi.mymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhi.mymall.beans.OrderItem;
import com.zhi.mymall.beans.Orders;
import com.zhi.mymall.mapper.OrderItemMapper;
import com.zhi.mymall.mapper.OrdersMapper;
import com.zhi.mymall.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.mymall.vo.OrderVO;
import com.zhi.mymall.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单项/快照  服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Override
    public ResultVO getByOrderId(String s) {
        QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
        orderItemQueryWrapper.eq("order_id",s);
        List<OrderItem> orderItem = orderItemMapper.selectList(orderItemQueryWrapper);
        return new ResultVO(10000,"查询成功",orderItem);
    }

    @Override
    public ResultVO getListByUserId(String s) {
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.eq("user_id",s);
        List<Orders> orders = ordersMapper.selectList(ordersQueryWrapper);
        ArrayList<OrderVO> orderVOS = new ArrayList<>();
        for (Orders order : orders) {
            QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
            orderItemQueryWrapper.eq("order_id",order.getOrderId());
            List<OrderItem> orderItems = orderItemMapper.selectList(orderItemQueryWrapper);
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order,orderVO);
            orderVO.setOrderItems(orderItems);
            orderVOS.add(orderVO);
        }
        return new ResultVO(10000,"查询成功",orderVOS);
    }

    @Override
    public boolean updateComment(String id, int status) {
        OrderItem orderItem = new OrderItem();
        orderItem.setIsComment(status);
        QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
        orderItemQueryWrapper.eq("item_id",id);
        if(orderItemMapper.update(orderItem,orderItemQueryWrapper)>0)return true;
        else return false;
    }
}
