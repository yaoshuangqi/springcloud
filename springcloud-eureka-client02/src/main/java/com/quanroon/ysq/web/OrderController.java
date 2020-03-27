package com.quanroon.ysq.web;

import com.quanroon.ysq.entity.Order;
import com.quanroon.ysq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "order/{orderId}")
    public Order queryOrderById(@PathVariable("orderId") String orderId) {
        return this.orderService.queryOrderById(orderId);
    }
}
