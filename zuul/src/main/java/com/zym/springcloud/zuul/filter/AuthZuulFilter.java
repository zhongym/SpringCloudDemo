package com.zym.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class AuthZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        boolean contains = context.getRequest().getRequestURI().contains("api/auth");
        return !contains;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        String token = context.getRequest().getHeader("token");
        if (token == null) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
        }
        return null;
    }
}
