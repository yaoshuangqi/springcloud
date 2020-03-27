package com.quanroon.ysq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/3/27 15:44
 * @content
 */
@SpringBootApplication//申明这是一个Spring Boot项目
@ComponentScan(basePackages = {"com.quanroon.ysq.web", "com.quanroon.ysq.service"})//手动指定bean扫描范围
@EnableEurekaClient
public class OrderApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }

    /**
     * 向Spring容器中定义RestTemplate对象
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
