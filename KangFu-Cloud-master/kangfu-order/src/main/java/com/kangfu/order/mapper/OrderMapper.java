package com.kangfu.order.mapper;

import com.kangfu.system.api.domain.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author kangfu
 */
public interface OrderMapper {

    /**
     * 查询单个订单
     * 根据orderId
     * @param orderId
     * @return
     */
    @Select("select orderId,content,userId,createtime from t_order where orderId = #{orderId}")
    Order getOrderByOrderId(@Param("orderId") Long orderId);

    /**
     * 查询订单列表
     * 根据userId
     * @param userId
     * @return
     */
    @Select("select orderId,content,userId,createtime from t_order where userId = #{userId}  order by createtime desc")
    List<Order> getOrderListByUserId(Long userId);

    /**
     * 更新单个订单
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
