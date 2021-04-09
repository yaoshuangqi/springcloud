package com.quanroon.ysq.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author quanroong.ysq
 * @version 1.0.0
 * @description
 * @createtime 2021/4/7 17:05
 */
@RestController
public class HelloController {

    @GetMapping("/say")
    public String sayHello(){
        return "spring-cloud-gateway-service:say hello";
    }
}
