package com.hryj;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author 李道云
 * @className: WeixinApplication
 * @description: 微信端网关启动入口
 * @create 2018-06-12 16:30
 **/
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class WeixinApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WeixinApplication.class).web(true).run(args);
    }
}
