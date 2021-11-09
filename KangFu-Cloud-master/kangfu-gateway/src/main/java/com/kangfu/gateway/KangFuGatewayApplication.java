package com.kangfu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 * 
 * @author kangfu
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class KangFuGatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(KangFuGatewayApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  康复网关启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
