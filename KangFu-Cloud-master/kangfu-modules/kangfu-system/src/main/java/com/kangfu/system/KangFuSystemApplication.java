package com.kangfu.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.kangfu.common.security.annotation.EnableCustomConfig;
import com.kangfu.common.security.annotation.EnableRyFeignClients;
import com.kangfu.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 * 
 * @author kangfu
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class KangFuSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(KangFuSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
