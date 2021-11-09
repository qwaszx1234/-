package com.kangfu.test.controller;

import com.kangfu.common.core.constant.SecurityConstants;
import com.kangfu.common.core.web.controller.BaseController;
import com.kangfu.common.core.web.domain.AjaxResult;
import com.kangfu.common.security.annotation.RequiresPermissions;
import com.kangfu.system.api.RemoteOrderService;
import com.kangfu.test.domain.User;
import com.kangfu.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController extends BaseController {

    @Autowired
    private RemoteOrderService remoteOrderService;
    @Autowired
    private UserService userService;

    /**
     * 查询订单列表
     * 根据userId
     * @path /test/order/list/{userId}
     * @param userId
     * @return
     */
    @GetMapping("/order/list/{userId}")
    public AjaxResult getOrderListByUserId(@PathVariable("userId") Long userId
            ,@RequestHeader(value = "origin",required = false)String origin) {
        //System.out.println("查询订单列表 origin = " + origin);
        return remoteOrderService.getOrderListByUserId(userId,SecurityConstants.FROM_SOURCE);
    }

    /**
     * 查询单个订单
     * 根据orderId
     * @path /test/order/query/{orderId}
     * @param orderId
     * @return
     */
    @GetMapping("/order/query/{orderId}")
    public AjaxResult getOrderByOrderId(@PathVariable("orderId") Long orderId,
        @RequestHeader(value = "origin",required = false)String origin){
        //System.out.println("查询单个订单 origin = " + origin);
        return remoteOrderService.getOrderByOrderId(orderId,SecurityConstants.FROM_SOURCE);
    }

    /**
     * 根据userId查询单个用户对象的信息
     * @path /test/order/queryUser/{userId}
     */
    @RequiresPermissions("test:user:query")
    @GetMapping("/order/queryUser/{userId}")
    public AjaxResult getUserByUserId(@PathVariable("userId") Long userId
            ,@RequestHeader(value = "origin",required = false)String origin) {
        //System.out.println("根据userId查询单个用户对象的信息 origin = " + origin);
        logger.info("根据userId查询单个用户对象的信息");
        User user = userService.selectUserById(userId);
        //logger.info("user = "+user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        return ajax;
    }

}
