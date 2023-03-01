package com.zhi.mymall.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhi.mymall.beans.Orders;
import com.zhi.mymall.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class AliPayTask {

    @Resource
    private OrdersService orderInfoService;


    /**
     * 从第0秒开始每隔30秒执行1次，查询创建超过5分钟，并且未支付的订单
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void orderConfirm(){
        Instant instant = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.le("create_time",instant);
        List<Orders> list = orderInfoService.list(ordersQueryWrapper);
        for (Orders orderInfo : list) {
            String orderNo = orderInfo.getOrderId();
            log.warn("超时订单 ===> {}", orderNo);

            //核实订单状态：调用支付宝查单接口
           orderInfoService.checkOrderStatus(orderNo);
        }
    }
}
