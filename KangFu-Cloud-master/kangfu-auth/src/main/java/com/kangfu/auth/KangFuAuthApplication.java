package com.kangfu.auth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.kangfu.common.security.annotation.EnableRyFeignClients;

/**
 * 认证授权中心
 * 
 * @author kangfu
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class KangFuAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(KangFuAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
