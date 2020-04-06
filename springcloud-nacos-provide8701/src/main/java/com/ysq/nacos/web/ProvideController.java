package com.ysq.nacos.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvideController {

    @GetMapping("/helloNacos")
    public String helloNacos(){
        return "你好，nacos！";
    }

}
