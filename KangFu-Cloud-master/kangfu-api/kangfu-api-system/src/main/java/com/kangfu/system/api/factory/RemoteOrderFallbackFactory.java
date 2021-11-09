package com.kangfu.system.api.factory;


import com.kangfu.common.core.domain.R;
import com.kangfu.common.core.web.domain.AjaxResult;
import com.kangfu.system.api.RemoteOrderService;
import com.kangfu.system.api.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 用户服务降级处理
 * 
 * @author kangfu
 */
@Component
public class RemoteOrderFallbackFactory implements FallbackFactory<RemoteOrderService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteOrderFallbackFactory.class);

    @Override
    public RemoteOrderService create(Throwable throwable)
    {
        log.error("订单服务调用失败:{}", throwable.getMessage());
        return new RemoteOrderService(){

            @Override
            public AjaxResult getOrderByOrderId(Long orderId, String source) {
                log.info("根据orderId获取Order失败");
                return AjaxResult.error("根据orderId获取Order失败:" + throwable.getMessage());
            }

            @Override
            public AjaxResult updateOrder(Order order, String source) {
                log.info("更新Order失败");
                return AjaxResult.error("更新Order失败:" + throwable.getMessage());
            }

            @Override
            public AjaxResult deleteOrderByIds(Long[] orderIds, String source) {
                log.info("删除Order失败");
                return AjaxResult.error("删除Order失败:" + throwable.getMessage());
            }

            @Override
            public AjaxResult insertOrder(Order order, String fromSource) {
                log.info("新增Order失败");
                return AjaxResult.error("新增Order失败:" + throwable.getMessage());
            }

            @Override
            public AjaxResult getOrderListByUserId(Long userId, String source) {
                log.info("根据userId获取Order列表失败");
                return AjaxResult.error("根据userId获取Order列表失败:" + throwable.getMessage());
            }
        };
    }
}
