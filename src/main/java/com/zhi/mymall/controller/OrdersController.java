package com.zhi.mymall.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.zhi.mymall.beans.Orders;
import com.zhi.mymall.service.OrdersService;
import com.zhi.mymall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 订单  前端控制器
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/retaildemo/orders")
@Api(tags = "订单管理")
@Slf4j
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Resource
    private AlipayClient alipayClient;

    @PostMapping(value = "/add", produces = "text/html;charset=utf-8")
    @ApiOperation("购物车结算生成订单,返回支付宝支付链接")
    public String add(String cartId, @RequestBody Orders orders, @RequestHeader("token") String token) {
        String s = ordersService.addOrder(cartId, orders);
        try {
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            Orders orderInfo = ordersService.getById(s);
            JSONObject bizContent = new JSONObject();
            request.setNotifyUrl("https://localhost:9090/retaildemo/orders/trade/notify");//回调接口
            request.setReturnUrl("http://localhost/paySuccess?orderId=" + s);
            bizContent.put("out_trade_no", orderInfo.getOrderId());
            BigDecimal total = new BigDecimal(orderInfo.getActualAmount());
            bizContent.put("total_amount", total);
            bizContent.put("subject", orderInfo.getUntitled());
            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
            request.setBizContent(bizContent.toString());
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                return response.getBody();
            } else {
                throw new RuntimeException("创建支付交易失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException("创建支付交易失败");
        }
    }

    @ApiOperation("支付通知")
    @PostMapping("/trade/notify")
    public String tradeNotify(@RequestParam Map<String, String> params) {


        String result = "failure";

        try {
            //异步通知验签
            boolean signVerified = AlipaySignature.rsaCheckV1(
                    params, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq748FfeHxVwExqW55TUvf8wlxi4I0sCvAAAN9x4nzO7pfSgzRFokCpc/LWBguzK04ny6AhQX7bbSkC2kmwdbcJgd5852MO5kd8Cdse+F9OnzJRbDpEi0aG3Xmco+s94VbHhUTvVrq0I4PCXEM6HWFZuz54pTZNS3mJ0v17SoF08HKLXlmmWXAsmcQnVHqmMO6PgPivRU5MMaLcNJbjOCCZfFPXoNEpmWxBxv5SRlK/zYx72fN/f0o9meUvEbhij6mi+alM16i97NyF0mLUGTSTutHqFpa33Uqo0zm3pqEWxYeUL8N4iUulio6e3ydZTH2+faZT6GusKb+yOqQbvAnQIDAQAB",
                    AlipayConstants.CHARSET_UTF8,
                    AlipayConstants.SIGN_TYPE_RSA2); //调用SDK验证签名

            if (!signVerified) {
                //验签失败则记录异常日志，并在response中返回failure.
                return result;
            }

            // 验签成功后

            //按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，
            //1 商户需要验证该通知数据中的 out_trade_no 是否为商户系统中创建的订单号
            String outTradeNo = params.get("out_trade_no");
            Orders order = ordersService.getById(outTradeNo);
            if (order == null) {
                return result;
            }

            //2 判断 total_amount 是否确实为该订单的实际金额（即商户订单创建时的金额）
            String totalAmount = params.get("total_amount");
            int totalAmountInt = new BigDecimal(totalAmount).intValue();
            int totalFeeInt = order.getActualAmount();
            if (totalAmountInt != totalFeeInt) {
                return result;
            }

            //3 校验通知中的 seller_id（或者 seller_email) 是否为 out_trade_no 这笔单据的对应的操作方
            String sellerId = params.get("seller_id");
            String sellerIdProperty = "2088132586661019";
            if (!sellerId.equals(sellerIdProperty)) {
                return result;
            }

            //4 验证 app_id 是否为该商户本身
            String appId = params.get("app_id");
            String appIdProperty = "2021003127629610";
            if (!appId.equals(appIdProperty)) {
                return result;
            }

            //在支付宝的业务通知中，只有交易通知状态为 TRADE_SUCCESS时，
            // 支付宝才会认定为买家付款成功。
            String tradeStatus = params.get("trade_status");
            if (!"TRADE_SUCCESS".equals(tradeStatus)) {
                return result;
            }

            //校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            result = "success";
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/getOrder")
    @ApiOperation("通过订单ID获取信息")
    public ResultVO getInfo(@RequestParam String orderId, @RequestHeader("token") String token) {
        Orders byId = ordersService.getById(orderId);
        return new ResultVO(10000, "获取订单信息成功", byId);
    }
}

