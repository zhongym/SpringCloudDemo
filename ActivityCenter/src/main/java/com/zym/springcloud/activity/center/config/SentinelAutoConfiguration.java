package com.zym.springcloud.activity.center.config;

import com.alibaba.cloud.sentinel.SentinelProperties;
import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import com.alibaba.csp.sentinel.adapter.servlet.CommonTotalFilter;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SentinelAutoConfiguration{

    @PostConstruct
    public void init() {
        WebCallbackManager.setUrlBlockHandler((request, response, blockException) -> {
            // request 里包含了此次请求所有的信息，可以从其中解析出URL、请求参数等。
            // response 表示响应对象，直接向其中写 fallback 结果即可。
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().println("{\"code\":401,\"msg\":\"请求过多\"}");
        });
    }

    /**
     * url总次数统计
     * @return
     */
    @Bean
    public FilterRegistrationBean<Filter> commonTotalFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.addUrlPatterns("/*");
        Filter filter = new CommonTotalFilter();
        registration.setFilter(filter);
        return registration;

    }
}