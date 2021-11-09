package com.kangfu.system.api;

import com.kangfu.common.core.constant.SecurityConstants;
import com.kangfu.common.core.constant.ServiceNameConstants;
import com.kangfu.common.core.domain.R;
import com.kangfu.common.core.web.domain.AjaxResult;
import com.kangfu.system.api.domain.Order;
import com.kangfu.system.api.factory.RemoteOrderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单服务
 * @author kangfu
 */
@FeignClient(contextId = "remoteOrderService", value = ServiceNameConstants.ORDER_SERVICE, fallbackFactory = RemoteOrderFallbackFactory.class)
public interface RemoteOrderService {


    /**
     * http://kangfu-order/order/list/{userId}
     * 查询订单列表
     * 根据userId
     * @path /order/list/{userId}
     * @param userId
     * @param source
     * @return
     */
    @GetMapping("/order/list/{userId}")
    AjaxResult getOrderListByUserId(@PathVariable("userId") Long userId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);


    /**
     * 查询单个订单
     * 根据orderId
     * @path /order/query/{orderId}
     * @param orderId
     * @param source
     * @return
     */
    @GetMapping("/order/query/{orderId}")
    AjaxResult getOrderByOrderId(@PathVariable("orderId") Long orderId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 更新单个订单
     * @path /order/order/update
     * @param order
     * @param source
     * @return
     */
    @PutMapping("/order/update")
    AjaxResult updateOrder(@RequestBody Order order, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 删除订单
     * @path /order/delete/{orderIds}
     * @param orderIds
     * @param source
     * @return
     */
    @DeleteMapping("/order/delete/{orderIds}")
    AjaxResult deleteOrderByIds(@PathVariable("orderIds") Long[] orderIds, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 新增订单
     * @path /order/insert
     * @param order
     * @param fromSource
     * @return
     */
    @PostMapping("/order/insert")
    AjaxResult insertOrder(@RequestBody Order order, @RequestHeader(SecurityConstants.FROM_SOURCE) String fromSource);
}
