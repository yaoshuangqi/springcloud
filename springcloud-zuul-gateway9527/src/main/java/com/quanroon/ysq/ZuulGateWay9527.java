package com.quanroon.ysq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/4/1 14:00
 * @content
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulGateWay9527 {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGateWay9527.class, args);
    }
}
