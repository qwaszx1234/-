package com.kangfu.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.kangfu.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 * 
 * @author kangfu
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class KangFuFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(KangFuFileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
