package com.kangfu.system.api.factory;

import com.kangfu.common.core.web.page.TableDataInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.kangfu.common.core.domain.R;
import com.kangfu.system.api.RemoteUserService;
import com.kangfu.system.api.domain.SysUser;
import com.kangfu.system.api.model.LoginUser;

import java.util.Collections;

/**
 * 用户服务降级处理
 * 
 * @author kangfu
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService() {
            @Override
            public R<LoginUser> getUserInfo(String username, String source) {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> registerUserInfo(SysUser sysUser, String source) {
                return R.fail("注册用户失败:" + throwable.getMessage());
            }

            @Override
            public TableDataInfo getUserList(String source) {
                return new TableDataInfo(Collections.emptyList(),0);
            }
        };
    }
}
