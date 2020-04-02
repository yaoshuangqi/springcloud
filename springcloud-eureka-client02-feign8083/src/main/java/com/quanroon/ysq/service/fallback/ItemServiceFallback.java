package com.quanroon.ysq.service.fallback;

import com.quanroon.ysq.entity.Item;
import com.quanroon.ysq.service.interfaces.ItemFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/4/2 13:47
 * @content 此类中的方法专门用于服务降级，该类一般要实现调用远程服务的接口（这样保证方法名一致）
 *
 * 结合Feign进行服务降级处理，需要开启Hystrix断路器
 */
@Component
public class ItemServiceFallback implements ItemFeignClient {

    /**
     * 服务降级的方法要和原方法一致(名称、参数列表)
     * @param id
     * @return
     */
    @Override
    public Item queryItemById(Long id) {

        return new Item(id, "通过类回调，查询商品信息出错!", null, null, null);
    }
}
