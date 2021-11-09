package com.kangfu.order.service;

import com.kangfu.system.api.domain.Order;

import java.util.List;

/**
 * @author kangfu
 */
public interface OrderService {
    /**
     * 根据订单id获取订单详细信息
     * @param userId
     * @return
     */
    Order getOrderByOrderId(Long userId);

    /**
     * 根据用户id获取订单列表
     * @param userId
     * @return
     */
    List<Order> getOrderListByUserId(Long userId);

    /**
     * 更新订单
     * @param order
     * @return
     */
    Integer updateOrder(Order order);

    /**
     * 删除订单
     * @param orderIds
     * @return
     */
    Integer deleteOrder(Long[] orderIds);

    /**
     * 新增订单
     * @param order
     * @return
     */
    Integer insertOrder(Order order);
}
