package com.quanroon.ysq.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注入我们自定义的负载均衡策略，默认后者覆盖前者，自定义覆盖默认的策略
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule MyRule(){
        return new LoadBalanceRule_YSQ();
    }
}
