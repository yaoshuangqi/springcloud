package com.quanroon.ysq.common;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤、服务拦截
 * 1、服务网关还有个作用就是接口的安全性校验，这个时候我们就需要通过 zuul 进行统一拦截，zuul 通过继承过滤器 ZuulFilter 进行处理：
 * 2、重写ZuulFilter 类中的 方法、
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/4/1 17:18
 * @content
 * filterType 为过滤类型，可选值有 pre（路由之前）、routing（路由之时）、post（路由之后）、error（发生错误时调用）。
 * filterOrdery 为过滤的顺序，如果有多个过滤器，则数字越小越先执行
 * shouldFilter 表示是否过滤，这里可以做逻辑判断，true 为过滤，false 不过滤
 * run 为过滤器执行的具体逻辑;
 *
 */
@Component
public class ApiFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

//    访问：http://localhost:9527/ysq/quanroon-item/provideApi/item/1 非法
//    访问 http://localhost:9527/ysq/quanroon-item/provideApi/item/1?token=123456 有效
    @Override
    public Object run() throws ZuulException {
        // 逻辑代码
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //  获取传递的参数、验证权限
        String token = request.getParameter("token");
        //
        if (!"123456".equals(token)) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(400);
            //  把提示信息显示到 页面
            try {
                currentContext.getResponse().getWriter().write("token is invalid");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
