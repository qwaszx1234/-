package com.kangfu.order.controller;

import com.kangfu.common.core.web.controller.BaseController;
import com.kangfu.common.core.web.domain.AjaxResult;
import com.kangfu.order.service.OrderService;
import com.kangfu.system.api.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 新增订单
     * @path /order/insert
     */
    //@RequiresPermissions("order:user:insert")
    @PostMapping(value = "/order/insert")
    public AjaxResult insertOrder(@RequestBody Order order
            ,@RequestHeader(value = "origin",required = false)String origin){
        logger.info("新增订单");
        //System.out.println("新增订单 origin = " + origin);
        Integer row = orderService.insertOrder(order);
        if(row>0){
            logger.info("新增订单成功！");
            AjaxResult ajaxResult = AjaxResult.success();
            ajaxResult.put("rows",row);
            return ajaxResult;
        }else{
            return AjaxResult.error("新增订单失败");
        }
    }

    /**
     * 删除订单
     * @path /order/delete
     */
    //@RequiresPermissions("order:user:delete")
    @DeleteMapping(value = "/order/delete/{orderIds}")
    public AjaxResult deleteOrder(@PathVariable("orderIds") Long[] orderIds
            ,@RequestHeader(value = "origin",required = false)String origin){
        logger.info("删除订单");
        //System.out.println("删除订单 origin = " + origin);
        Integer row = orderService.deleteOrder(orderIds);
        if(row>0){
            logger.info("删除订单成功！");
            return toAjax(row);
        }else{
            logger.info("删除订单失败！");
            return toAjax(0);
        }
    }

    /**
     * 更新单个订单
     * @path /order/update
     */
    //@RequiresPermissions("order:user:update")
    @PutMapping(value = "/order/update")
    public AjaxResult updateOrder(@RequestBody Order order
            ,@RequestHeader(value = "origin",required = false)String origin){
        logger.info("更新单个订单");
        //System.out.println("更新单个订单 origin = " + origin);
        Integer row = orderService.updateOrder(order);
        if(row>0){
            logger.info("更新单个订单成功！");
            AjaxResult ajaxResult = AjaxResult.success();
            ajaxResult.put("rows",row);
            return ajaxResult;
        }else{
            return AjaxResult.error("更新单个订单失败");
        }
    }

    /**
     * 查询单个订单
     * 根据orderId
     * @path /order/query/{orderId}
     */
    //@RequiresPermissions("order:user:query")
    @GetMapping(value = "/order/query/{orderId}")
    public AjaxResult getOrderByOrderId(@PathVariable("orderId") Long orderId,
        @RequestHeader(value = "origin",required = false)String origin){
        logger.info("根据orderId查询单个订单");
        //System.out.println("根据orderId查询单个订单 origin = " + origin);
        startPage();
        Order order = orderService.getOrderByOrderId(orderId);
        AjaxResult ajaxResult = AjaxResult.success();

        if(order!=null){
            ajaxResult.put("order",order);
            return ajaxResult;
        }else{
            return AjaxResult.error("查询结果为空");
        }
    }

    /**
     * 查询订单列表
     * 根据userId
     * @path /order/list/{userId}
     */
    //@RequiresPermissions({"order:user:list"})
    @GetMapping(value = "/order/list/{userId}")
    public AjaxResult getOrderListByUserId(@PathVariable("userId") Long userId
            ,@RequestHeader(value = "origin",required = false)String origin){
        logger.info("根据userId查询订单列表");
        //System.out.println("根据userId查询订单列表 origin = " + origin);
        startPage();
        List<Order> orderList = orderService.getOrderListByUserId(userId);
        AjaxResult ajaxResult = AjaxResult.success();
        if(orderList.size() > 0){
            ajaxResult.put("orders",orderList);
            return ajaxResult;
        }else{
            return AjaxResult.error("查询结果为空");
        }
    }
}
