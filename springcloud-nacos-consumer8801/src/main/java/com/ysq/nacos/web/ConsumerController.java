package com.ysq.nacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String test1() {
        return restTemplate.getForObject("http://nacos-provide/helloNacos",String.class);
    }
}
