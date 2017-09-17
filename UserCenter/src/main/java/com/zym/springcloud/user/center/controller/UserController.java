package com.zym.springcloud.user.center.controller;

import com.netflix.discovery.EurekaClient;
import com.zym.springcloud.user.center.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{userId}")
    public User get(@PathVariable("userId") Long userId) {
        User user = new User();
        user.setUserId(130L);
        user.setUserName("zym");
        System.out.println(discoveryClient.getLocalServiceInstance().getHost());
        return user;
    }


}
