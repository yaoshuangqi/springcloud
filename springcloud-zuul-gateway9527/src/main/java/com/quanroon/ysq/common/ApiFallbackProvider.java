package com.quanroon.ysq.common;


import com.netflix.zuul.context.RequestContext;
import org.json.JSONObject;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * zuul 为我们提供了一个名叫 ZuulFallbackProvider 的接口、 在服务挂掉时候调用该接口返回信息，
 * 我们通过实现该接口，重写接口中fallbackResponse 方法，返回错误的处理规则、给出信息提示：
 *
 * 当我们的zuul进行路由分发时，如果后端服务没有启动，或者调用超时，这时候我们希望Zuul提供一种降级功能，而不是将异常暴露出来。
 * Spring cloud zuul提供这种降级功能，操作步骤如下：
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/4/1 16:55
 * @content
 */
@Component
public class ApiFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        return "*";// api服务id，如果需要所有调用都支持回退，则return "*"或return null
    }
    /**
     * 如果请求用户服务失败，返回什么信息给消费者客户端
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        return new ClientHttpResponse() {

            @Override
            public InputStream getBody() throws IOException {
                JSONObject r = new JSONObject();
                r.put("state", "8888");
                r.put("msg", "系统错误，请求失败");
                System.out.println("降级"+r.toString());
                return new ByteArrayInputStream(r.toString().getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                // 和body中的内容编码一致，否则容易乱码
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }

            /**
             * 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的， 不应该把api的404,500等问题抛给客户端
             * 网关和api服务集群对于客户端来说是黑盒子
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {

                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {

                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {

                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

        };
    }
}
