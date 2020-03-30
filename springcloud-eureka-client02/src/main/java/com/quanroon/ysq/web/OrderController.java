package com.quanroon.ysq.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.quanroon.ysq.entity.Item;
import com.quanroon.ysq.entity.Order;
import com.quanroon.ysq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/3/27 15:49
 * @content
 */
@RestController
public class OrderController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private LoadBalancerClient loadBalancerClient;//自动注入

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "order/{orderId}")
    public Order queryOrderById(@PathVariable("orderId") String orderId) {

        ServiceInstance serviceInstance = this.loadBalancerClient.choose("app-item");
        System.out.println("测试负载均衡的策略模式》调用的app-item服务：" + serviceInstance.getHost() + ": " + serviceInstance.getPort());

        return this.orderService.queryOrderById(orderId);
    }

    @GetMapping(value = "hystrix/{orderId}")
    public Order queryOrderById2(@PathVariable("orderId") String orderId) {
        return this.orderService.queryOrderById2(orderId);
    }
}
