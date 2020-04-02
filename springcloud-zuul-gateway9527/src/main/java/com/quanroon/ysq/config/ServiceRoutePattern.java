package com.quanroon.ysq.config;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/4/1 20:30
 * @content .使用正则表达式指定路由规则
 * "(?<name>^.+)-(?<version>v.+$)":微服务名称正则规则如：app-item-v2 --->> 映射到/v2/app-item/**
 * 注意：这样配置后，就不能通过微服务名称进行访问了。同时也不需要在applicaiton.yml中配置路由规则了。
 * PatternServiceRouteMapper路由规则会覆盖其application.yml中的规则(即yml文件中的路由规则无效)
 */
//@Configuration  //需要时打开配置
public class ServiceRoutePattern {

    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }
}
