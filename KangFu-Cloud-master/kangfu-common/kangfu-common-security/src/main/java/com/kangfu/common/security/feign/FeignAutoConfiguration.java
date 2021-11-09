package com.kangfu.common.security.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.RequestInterceptor;

/**
 * Feign 配置注册
 *
 * @author kangfu
 **/
@Configuration
public class FeignAutoConfiguration
{
    /**
     * feign请求拦截器
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor()
    {
        return new FeignRequestInterceptor();
    }
}
