package com.kangfu.test;

import com.kangfu.common.security.annotation.EnableCustomConfig;
import com.kangfu.common.security.annotation.EnableRyFeignClients;
import com.kangfu.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 测试模块
 * @author kangfu
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class TestServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestServiceApplication.class);
        System.out.println("(♥◠‿◠)ﾉﾞ  测试模块启动成功   ლ(´ڡ`ლ)");
    }
}