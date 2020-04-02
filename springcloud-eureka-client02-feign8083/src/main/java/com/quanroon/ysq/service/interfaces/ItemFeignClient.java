package com.quanroon.ysq.service.interfaces;

import com.quanroon.ysq.entity.Item;
import com.quanroon.ysq.service.fallback.ItemServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/4/2 11:18
 * @content 申明这是一个Feign客户端，并且指明服务id
 * 通过Feign实现RESTFUL API的调用，代替了restTemplate
 * fallback:配合Hystrix进行服务降级
 */
@FeignClient(value = "app-item",fallback = ItemServiceFallback.class)
public interface  ItemFeignClient {

    /**
     * 这里定义了类似于SpringMVC用法的方法，就可以进行RESTful方式的调用了
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/provideApi/item/{id}", method = RequestMethod.GET)
    Item queryItemById(@PathVariable("id") Long id);
}
