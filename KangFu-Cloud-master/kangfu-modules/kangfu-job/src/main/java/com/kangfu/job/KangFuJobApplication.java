package com.kangfu.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.kangfu.common.security.annotation.EnableCustomConfig;
import com.kangfu.common.security.annotation.EnableRyFeignClients;
import com.kangfu.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 * 
 * @author kangfu
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class KangFuJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(KangFuJobApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
