package com.quanroon.ysq.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 自定义负载均衡策略，
 * 注意：这个类不能被ComponentScan扫描到。否者被所有RibbonClient共享，达不到特定的目的
 */
public class LoadBalanceRule_YSQ extends AbstractLoadBalancerRule {

    private int total = 0;            // 总共被调用的次数，目前要求每台被调用5次
    private int currentIndex = 0;    // 当前提供服务的机器号
    /**
     * 需求： 两台微服务，消费者每次轮询5次换下一个服务。以此循环
     *
     * total = 0 // 当total==5以后，我们指针才能往下走，
     * index = 0 // 当前对外提供服务的服务器地址，
     * total需要重新置为零，但是已经达到过一个5次，我们的index = 1
     * @param lb
     * @param key
     * @return
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();//获取活着的服务
                List<Server> allList = lb.getAllServers();//获取所有服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }
                //自定义策略算法++++++++start+++++++++
                if(total < 5){
                    server = upList.get(currentIndex);
                    total++;
                }else{
                    total = 0;
                    currentIndex++;
                    if(currentIndex >= allList.size()){
                        currentIndex = 0;

                    }
                }
                //+++++++++++++++end+++++++++++++++++
                /*int index = this.rand.nextInt(serverCount);
                server = (Server)upList.get(index);*/

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    @Override
    public Server choose(Object o) {
        return this.choose(this.getLoadBalancer(), o);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }


}
