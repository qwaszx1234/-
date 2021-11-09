package com.kangfu.system.api.factory;

import com.kangfu.common.core.domain.R;
import com.kangfu.common.core.web.domain.AjaxResult;
import com.kangfu.system.api.RemoteTestService;
import com.kangfu.system.api.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * 测试服务降级处理
 * @author kangfu
 */
public class RemoteTestFallbackFactory implements FallbackFactory<RemoteTestService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteTestFallbackFactory.class);
    @Override
    public RemoteTestService create(Throwable throwable) {
        log.error("测试服务调用失败:{}", throwable.getMessage());
        return new RemoteTestService()
        {
            @Override
            public R<LoginUser> getUserList(String source) {
                return R.fail("获取失败:" + throwable.getMessage());
            }

            @Override
            public AjaxResult getOrderByOrderId(Long orderId, String source) {
                return AjaxResult.error();
            }
        };
    }
}
