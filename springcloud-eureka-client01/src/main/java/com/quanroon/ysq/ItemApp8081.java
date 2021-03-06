package com.quanroon.ysq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/3/27 15:13
 * @content 服务提供者
 */
@SpringBootApplication
@EnableEurekaClient
public class ItemApp8081 {
    public static void main(String[] args) {
        SpringApplication.run(ItemApp8081.class, args);
    }
}
