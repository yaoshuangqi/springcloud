package com.quanroon.ysq.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.quanroon.ysq.entity.Item;
import com.quanroon.ysq.service.interfaces.ItemFeignClient;
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
    //注入Feign客户端，优雅使用接口编程调用restful API
    @Autowired
    private ItemFeignClient itemFeignClient;

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

    //------------------------------------------------------------------Hystrix测试----------------------------------------------------------------------------
    /**
     * 进行容错处理
     * fallbackMethod的方法参数个数类型要和原方法一致
     * 如果http://app-item/item/{id}这个微服务不可用，则该方法就会回调容错处理，而不是直接报服务器500
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById2(Long id) {
        //String itemUrl = "http://app-item/item/{id}";//这个地址不可用,测试容错处理，是否回调
        String itemUrl = "http://app-item/provideApi/item/{id}";
        Item result = restTemplate.getForObject(itemUrl, Item.class, id);
        System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
        return result;
    }
    //----------------------------------------------------------------------Feign测试---------------------------------------------------------------

    public Item queryItemById3(Long id) {
        Item result = itemFeignClient.queryItemById(id);
        return result;
    }


    //----------------------------------------------------------------------Hystrix容错回调处理，还有一种方法就是专门写个类来处理，结合Feign---------------------------------------------------------------

    /**
     * 请求失败执行的方法
     * fallbackMethod的方法参数个数类型要和原方法一致
     *
     * @param id
     * @return
     */
    public Item queryItemByIdFallbackMethod(Long id) {
        return new Item(id, "查询商品信息出错!", null, null, null);
    }
}
