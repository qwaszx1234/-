package com.kangfu.order.service;

import com.kangfu.system.api.domain.Order;

import java.util.List;

public interface OrderService {
    Order getOrderByOrderId(Long userId);

    List<Order> getOrderListByUserId(Long userId);

    Integer updateOrder(Order order);

    Integer deleteOrder(Long[] orderIds);

    Integer insertOrder(Order order);
}
