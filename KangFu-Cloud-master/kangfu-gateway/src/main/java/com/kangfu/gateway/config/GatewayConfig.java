package com.kangfu.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.WebFluxCallbackManager;
import com.kangfu.gateway.handler.SentinelFallbackHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.function.Function;

/**
 * 网关限流配置
 * 
 * @author kangfu
 */
@Configuration
public class GatewayConfig
{

    /**
     * 降级处理
     * 自定义限流异常处理
     * 为了展示更加友好的限流提示， Sentinel支持自定义异常处理
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler()
    {
        return new SentinelFallbackHandler();
    }

    /**
     * 该方法在类构造函数调用之后调用
     * GatewayCallbackManager 的优先级低于 FallbackFactory 的实现类
     * 此处配置的 BlockRequestHandler 不会生效，会调用 FallbackFactory 实现类中的方法。
     */
    //@PostConstruct
    public void init(){
        // 限流回调函数
        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler() {
            // 当请求被限流时调用的方法
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                return ServerResponse.status(123).bodyValue("系统繁忙，请稍后");
            }
        });
        // 授权规则回调函数
        WebFluxCallbackManager.setRequestOriginParser(new Function<ServerWebExchange, String>() {
            @Override
            public String apply(ServerWebExchange serverWebExchange) {
                System.out.println("gateway WebFluxCallbackManager apply");
                return serverWebExchange.getRequest().getHeaders().get("origin").toString();
            }
        });
    }
}