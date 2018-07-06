package com.zym.springcloud.user.center.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.EurekaClient;
import com.zym.springcloud.user.center.domain.User;
import com.zym.springcloud.user.center.event.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by zhong on 2017/9/5.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{userId}")
    public User get(@PathVariable("userId") Long userId) {
        User user = new User();
        user.setUserId(130L);
        user.setUserName("port:" + discoveryClient.getLocalServiceInstance().getPort());
        System.out.println(discoveryClient.getLocalServiceInstance().getHost());
        return user;
    }

    @GetMapping("/save")
    public User save(@RequestParam("id")Long id, User user) {
        applicationContext.publishEvent(new OrderEvent(12L));
        return user;
    }

    @PostMapping("/save")
    public User save(HttpServletRequest request) throws IOException {
        User user1 = objectMapper.readValue(request.getInputStream(), User.class);
        return user1;
    }

}
