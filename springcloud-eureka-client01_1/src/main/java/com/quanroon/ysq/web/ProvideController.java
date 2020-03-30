package com.quanroon.ysq.web;

import com.quanroon.ysq.entity.Item;
import com.quanroon.ysq.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/3/27 15:24
 * @content 服务提供者
 */
@RestController
@RequestMapping("provideApi/")
public class ProvideController {

    @Autowired
    private ItemService itemService;

    /**
     * 对外提供接口服务，查询商品信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "item/{id}")
    public Item queryItemById(@PathVariable("id") Long id) {
        return this.itemService.queryItemById(id);
    }
}
