package com.zym.springcloud.eureka.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping("/get")
    public Map<String, String> get() {
        Map<String, String> hashMap = new HashMap<>(1);
        hashMap.put("key","value");
        return hashMap;
    }
}
