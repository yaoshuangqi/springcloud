package com.quanroon.ysq.service;

import com.quanroon.ysq.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/3/27 15:48
 * @content
 */
@Service
public class ItemService {
    // Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;

//    public Item queryItemById(Long id) {
//        return this.restTemplate.getForObject("http://127.0.0.1:8081/provideApi/item/"
//                + id, Item.class);
//    }

    public Item queryItemById(Long id) {

        // 该方法走eureka注册中心调用(去注册中心根据app-item查找服务，这种方式必须先开启负载均衡@LoadBalanced)
        String itemUrl = "http://app-item/provideApi/item/{id}";
        Item result = restTemplate.getForObject(itemUrl, Item.class, id);
        System.out.println("订单系统调用商品服务,result:" + result);
        return result;
    }
}
