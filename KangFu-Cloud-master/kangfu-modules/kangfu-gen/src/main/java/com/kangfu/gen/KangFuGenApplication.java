package com.kangfu.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.kangfu.common.security.annotation.EnableCustomConfig;
import com.kangfu.common.security.annotation.EnableRyFeignClients;
import com.kangfu.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 * 
 * @author kangfu
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class KangFuGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(KangFuGenApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  代码生成模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
