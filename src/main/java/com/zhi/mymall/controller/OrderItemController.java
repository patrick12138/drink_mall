package com.zhi.mymall.controller;


import com.zhi.mymall.service.OrderItemService;
import com.zhi.mymall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 订单项/快照  前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/retaildemo/order-item")
@Api(tags = "订单详情页")
public class OrderItemController {
    @Resource
    private OrderItemService orderItemService;

    @GetMapping("/getOrderDetail")
    @ApiOperation("通过订单ID查询订单详细信息")
    public ResultVO getInfo(@RequestParam String orderId, @RequestHeader("token") String token) {
        return orderItemService.getByOrderId(orderId);
    }

    @GetMapping("/getOrderListByUserId/{id}")
    @ApiOperation("通过用户ID查询所有订单")
    public ResultVO getInfos(@PathVariable("id") String userId, @RequestHeader("token") String token) {
        return orderItemService.getListByUserId(userId);
    }

    @GetMapping("/updateComment")
    @ApiOperation("修改是否评论状态")
    public boolean updateComment(@RequestParam String itemId, @RequestParam int isComment, @RequestHeader("token") String token) {
        return orderItemService.updateComment(itemId, isComment);
    }
}

