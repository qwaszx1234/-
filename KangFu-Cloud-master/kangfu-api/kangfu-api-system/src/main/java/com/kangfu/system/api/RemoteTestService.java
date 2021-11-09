package com.kangfu.system.api;

import com.kangfu.common.core.constant.SecurityConstants;
import com.kangfu.common.core.constant.ServiceNameConstants;
import com.kangfu.common.core.domain.R;
import com.kangfu.common.core.web.domain.AjaxResult;
import com.kangfu.system.api.factory.RemoteTestFallbackFactory;
import com.kangfu.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 测试服务
 * 限流或降级的回调类
 * 声明一个对remoteTestService远程调用的接口，封装所有对remoteTestService发起的远程调用
 *
 * @author kangfu
 */
@FeignClient(contextId = "remoteTestService", value = ServiceNameConstants.TEST_SERVICE,fallbackFactory= RemoteTestFallbackFactory.class)
public interface RemoteTestService {

    @GetMapping("/user/list")
    public R<LoginUser> getUserList(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 查询单个订单
     * 根据orderId
     * @path /order/query/{orderId}
     * @param orderId
     * @return
     */
    @GetMapping("/order/query/{orderId}")
    public AjaxResult getOrderByOrderId(@PathVariable("orderId") Long orderId,@RequestHeader(SecurityConstants.FROM_SOURCE) String source);


}
