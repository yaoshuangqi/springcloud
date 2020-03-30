package com.quanroon.ysq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/3/27 14:28
 * @content Eureka注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class AppEureka8200 {
    public static void main(String[] args) {
        SpringApplication.run(AppEureka8200.class,args);
    }
}
