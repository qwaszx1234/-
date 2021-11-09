package com.kangfu.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.kangfu.order.mapper.OrderMapper;
import com.kangfu.order.service.OrderService;
import com.kangfu.system.api.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询单个订单
     * 根据orderId
     */
    @Override
    @SentinelResource(value = "orderquery")//, blockHandler = "getOrderByOrderIdBlockHandler", fallback = "getOrderByOrderIdFallBack")
    public Order getOrderByOrderId(Long orderId) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toSeconds(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orderMapper.getOrderByOrderId(orderId);
    }
    // 服务流量控制处理，参数最后多一个 BlockException，其余与原函数一致。
    public Order getOrderByOrderIdBlockHandler(Long orderId, BlockException be) {
        System.out.println("查询单个订单getOrderByOrderId服务流量控制处理");
        return new Order();
    }
    // 服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
    public Order getOrderByOrderIdFallBack(Long orderId,Throwable throwable) {
        System.out.println("查询单个订单getOrderByOrderId服务熔断降级处理");
        return new Order();
    }

    /**
     * 查询订单列表
     * 根据userId
     */
    @Override
    @SentinelResource(value = "orderlist", blockHandler = "getOrderListByUserIdBlockHandler", fallback = "getOrderListByUserIdFallBack")
    public List<Order> getOrderListByUserId(Long userId) {
        return orderMapper.getOrderListByUserId(userId);
    }
    // 服务流量控制处理，参数最后多一个 BlockException，其余与原函数一致。
    public Order getOrderListByUserIdBlockHandler(Long orderId, BlockException be) {
        System.out.println("查询订单列表getOrderListByUserId服务流量控制处理");
        return new Order();
    }
    // 服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
    public List<Order> getOrderListByUserIdFallBack(Long userId, Throwable throwable) {
        System.out.println("查询订单列表getOrderListByUserId服务熔断降级处理");
        return Collections.emptyList();
    }

    /**
     * 更新单个订单
     */
    @Override
    public Integer updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    /**
     * 删除订单
     * @param orderIds
     * @return
     */
    @Override
    public Integer deleteOrder(Long[] orderIds) {
        return orderMapper.deleteOrder(orderIds);
    }

    /**
     * 新增订单
     * @param order
     * @return
     */
    @Override
    public Integer insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }
}
