package com.zhi.mymall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhi.mymall.beans.OrderItem;
import com.zhi.mymall.beans.Orders;
import com.zhi.mymall.beans.Product;
import com.zhi.mymall.beans.ProductSku;
import com.zhi.mymall.mapper.*;
import com.zhi.mymall.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.mymall.vo.ShoppingCartVO;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 订单  服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductMapper productMapper;
    @Resource
    private AlipayClient alipayClient;


    @Override
    @Transactional
        public String addOrder(String cartId,Orders order){
            ArrayList<ShoppingCartVO> shoppingCartVOS = new ArrayList<>();
            String[] arr = cartId.split(",");
            List<Integer> cidsList = new ArrayList<>();
            for (int i = 0; i <arr.length ; i++) {
                cidsList.add(Integer.parseInt(arr[i]));
            }
            for (Integer integer : cidsList) {
                ShoppingCartVO byCartIdOnOrder = shoppingCartMapper.getByCartIdOnOrder(integer.toString());
                shoppingCartVOS.add(byCartIdOnOrder);
            }
            boolean f = true;
            String untitled = "";
            for (ShoppingCartVO sc: shoppingCartVOS) {
                if(Integer.parseInt(sc.getCartNum()) > sc.getStock()){
                    f = false;
                }
                //获取所有商品名称，以,分割拼接成字符串
                untitled = untitled+sc.getProductName()+",";
            }
               order.setUntitled(untitled);
                order.setStatus("1");
                 ordersMapper.insert(order);
                 //3.生成商品快照
                for (ShoppingCartVO sc: shoppingCartVOS) {
                    int cnum = Integer.parseInt(sc.getCartNum());
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(order.getOrderId());
                    BeanUtils.copyProperties(sc,orderItem);
                    orderItem.setProductImg(sc.getUrl());
                    orderItem.setBasketDate(sc.getCartTime());
                    orderItem.setProductPrice(new BigDecimal(sc.getSellPrice()));
                    orderItem.setBuyCounts(cnum);
                    orderItem.setTotalAmount(new BigDecimal(sc.getSellPrice() * cnum));
                    orderItem.setIsComment(0);
                    orderItemMapper.insert(orderItem);
                    //增加商品销量
                    QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("product_id",sc.getProductId());
                    Product product = productMapper.selectOne(queryWrapper);
                    product.setSoldNum(product.getSoldNum()+cnum);
                }
                //4.扣减库存：根据套餐ID修改套餐库存量
                for (ShoppingCartVO sc: shoppingCartVOS) {
                    String skuId = sc.getSkuId();
                    int newStock = sc.getStock()- Integer.parseInt(sc.getCartNum());
                    ProductSku productSku = new ProductSku();
                    productSku.setSkuId(skuId);
                    productSku.setStock(newStock);
                    productSkuMapper.updateById(productSku);
                }
                for (Integer integer : cidsList) {
                    shoppingCartMapper.deleteById(integer);
                }
                return order.getOrderId();
    }

    /**
     * 查询订单
     * @param orderNo
     * @return 返回订单查询结果，如果返回null则表示支付宝端尚未创建订单
     */
    @Override
    public String queryOrder(String orderNo) {

        try {

            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", orderNo);
            request.setBizContent(bizContent.toString());

            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                return response.getBody();
            } else {
                 //throw new RuntimeException("查单接口的调用失败");
                return null;//订单不存在
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException("查单接口的调用失败");
        }
    }
    /**
     * 关单接口的调用
     * @param orderNo 订单号
     */
    private void closeOrder(String orderNo) {

        try {
            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", orderNo);
            request.setBizContent(bizContent.toString());
            AlipayTradeCloseResponse response = alipayClient.execute(request);

            if(response.isSuccess()){
            } else {
               //throw new RuntimeException("关单接口的调用失败");
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException("关单接口的调用失败");
        }
    }

    /**
     * 根据订单号调用支付宝查单接口，核实订单状态
     * 如果订单未创建，则更新商户端订单状态
     * 如果订单未支付，则调用关单接口关闭订单，并更新商户端订单状态
     * 如果订单已支付，则更新商户端订单状态，并记录支付日志
     * @param orderNo
     */
    @Override
    public void checkOrderStatus(String orderNo) {


        String result = this.queryOrder(orderNo);
        Orders orders = new Orders();
        orders.setOrderId(orderNo);
        //订单未创建
        if(result == null){
            //更新本地订单状态
            orders.setStatus("1");
            ordersMapper.updateById(orders);
        }
        else {
            //解析查单响应结果
            Gson gson = new Gson();
            HashMap<String, LinkedTreeMap> resultMap = gson.fromJson(result, HashMap.class);
            LinkedTreeMap alipayTradeQueryResponse = resultMap.get("alipay_trade_query_response");

            String tradeStatus = (String) alipayTradeQueryResponse.get("trade_status");
            if (Objects.equals(tradeStatus, "WAIT_BUYER_PAY")) {

                //如果订单未支付，则调用关单接口关闭订单
                this.closeOrder(orderNo);

                // 并更新商户端订单状态
                orders.setStatus("2");
                ordersMapper.updateById(orders);
            }

            if (Objects.equals(tradeStatus, "TRADE_SUCCESS")) {
                orders.setStatus("4");
                ordersMapper.updateById(orders);
            }
        }
    }

    @Override
    @Transactional
    public String tradeCreate(String orderId) {
/*
        try {
            //生成订单
            log.info("生成订单");
            //调用支付宝接口
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            //配置需要的公共请求参数
*//*            //支付完成后，支付宝向谷粒学院发起异步通知的地址
            request.setNotifyUrl(config.getProperty("alipay.notify-url"));
            //支付完成后，我们想让页面跳转回谷粒学院的页面，配置returnUrl
            request.setReturnUrl(config.getProperty("alipay.return-url"));*//*
            Orders orderInfo= ordersMapper.selectById(orderId);
            request.setNotifyUrl("");
            request.setReturnUrl("");
            //组装当前业务方法的请求参数
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", orderInfo.getOrderId());
            BigDecimal total = new BigDecimal(orderInfo.getActualAmount());
            bizContent.put("total_amount", total);
            bizContent.put("subject", orderInfo.getUntitled());
            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
*//*            bizContent.put("out_trade_no", "20210817010101004");
            bizContent.put("total_amount", 0.01);
            bizContent.put("subject", "123");
            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");*//*

            request.setBizContent(bizContent.toString());
*//*            AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2021003127629610",
                    "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAKNtCfbvmmNLZaUvxTmSjO3PAVjB/fuLek86zlG6reybYKqYw9yP3V+hzf5pVsvJN5IZxxEkcsJ3r1CH1/QyTxYu66wDxYlTsms+CQ123OFG4NnFFwJ5Q5HhuY+C6o5yXo7BkWOHja6zNtZqR5L4uEtXtnGmJq19BCHloExv9wSbcK58Nj1iNgakocxATI4HlrXXGuh4gtHLiFtNzXu/0iGa9GtYWBtXQE8YbvyjGKVCvcDfu29CB38qbmDsdVFffpZkvyLvniTGXVvd9pzQEWRZQgLHFC2IewJGcc1BJDh+1V7oxBTzcSmXwyrPmSDC6vmhS8kL4C0XkpWtn1titAgMBAAECggEANxXYkG7MueQMFMjOjIyecw7VxOEG/XYDe5naaZOuMeTAIH/MaRWBDwpo7mP1aUp+HX7RTssIhd1gM4I9iqbWphlt4syp3hbe28f600mzWVJKxbwY2dtd8PUS0pf5ex9h6kfARJFznjWk09Jv+q9wEwujifsF3decHK7QyCwWhu3GwJa4sIb+wiAAVzrfLWCecGa6XASQ6C6NW6Z8NomNQ7OAOSoMpi88r73zwKtUbZFCg2C3yPh8wjI78GYOV50GqA0E8mFVqVGZ0cPePKqRU3zMFJ9Wl7IjWDPW09+UiAZj3E9wuaYB7G97dgeLa1Bprfu0WzhnN5sCWbsVVqxpnQKBgQDL8gS/IzelcPgLHFNpKqCh8VrwMsFgv88DfNO9sHPbD4esQbdXZVW9DI/aEQa0ZJs7HBMyXISkbsUTu3Wfl98Q9R7+47ZfXIwu3/5a/7MUNZOQstnUB/kMAVH7A3+wbCLovhsXXwGIQP1ApcSd8OLWdH7kqBE0CiG8rwh5DI4umwKBgQCg3uyCTC/YndgIlXylVXEgq31UFkkDyRqtRiDCx1P+zvLJwxAyKHIJRyiJo/GLaTgTHbeQZx/kbXwQV1eztR8Q1SwDdbzXzoKDG7LfkyYLczfHvtQMvc0V3meuXX6Fl9dzB/cTe8o3+pOIgI1UUl4jBlse34Tf7RVLjLe4Jm4mVwKBgDj52xOe/hvNLK7F8hOOYiP77qDuPRIPZAEgbXMwCZ3of8nC7jqPR92x1bphSVM6TBe+ZyqJqFDb1VkG5Px6DEqLgGZLLZcEnvE1iLTZfKknspsZLDvNImmVMNcEija6byJAggcMdrwfE+FsrjpFosmw8AZnYa79KaGx8JcIx+wDAoGAE5BIrufZAbzgYtrbF7g2U8THLwyuwHkad2wJ2+Ylvt0oBYVlWNQ2Wg5/TY3UeCHJofmBM1x3Mm5mIg6dKcujBYcHHTyaRJxmns8lh677d3UBmZcJbqs+X6R2c2wHUPPQTbrwPzxUsU4V1wnbJiAGNrg1KKICJMFOT6vDQ+/YgW0CgYEAk37VR9C2yCPd87p0BergO4lpEv+tPr667YL4b0IGoofGq96cS4FSyqTyyVaLSwvDOPI71XVwyjRKYTEW478DMGKxMVoodapmNmQ5sTp/NMlqdvUf2CZAEKLuGlWvlKXalxOxbAVEdg6N8/Df5p/hMO3AcE2D0pClscrOr0G8by8=",
                    "json","GBK","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq748FfeHxVwExqW55TUvf8wlxi4I0sCvAAAN9x4nzO7pfSgzRFokCpc/LWBguzK04ny6AhQX7bbSkC2kmwdbcJgd5852MO5kd8Cdse+F9OnzJRbDpEi0aG3Xmco+s94VbHhUTvVrq0I4PCXEM6HWFZuz54pTZNS3mJ0v17SoF08HKLXlmmWXAsmcQnVHqmMO6PgPivRU5MMaLcNJbjOCCZfFPXoNEpmWxBxv5SRlK/zYx72fN/f0o9meUvEbhij6mi+alM16i97NyF0mLUGTSTutHqFpa33Uqo0zm3pqEWxYeUL8N4iUulio6e3ydZTH2+faZT6GusKb+yOqQbvAnQIDAQAB","RSA2");
            //执行请求，调用支付宝接口*//*
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);

            if(response.isSuccess()){
                log.info("调用成功，返回结果 ===> " + response.getBody());
                return response.getBody();
            } else {
                log.info("调用失败，返回码 ===> " + response.getCode() + ", 返回描述 ===> " + response.getMsg());
                throw new RuntimeException("创建支付交易失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException("创建支付交易失败");
        }
    }*/
        return "";}
}
