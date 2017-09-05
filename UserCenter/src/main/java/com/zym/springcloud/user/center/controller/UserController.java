package com.zym.springcloud.user.center.controller;

import com.zym.springcloud.user.center.domain.User;
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

    @GetMapping("/{userId}")
    public User get(@PathVariable("userId") Long userId) {
        User user = new User();
        user.setUserId(130L);
        user.setUserName("zym");
        return user;
    }


}
