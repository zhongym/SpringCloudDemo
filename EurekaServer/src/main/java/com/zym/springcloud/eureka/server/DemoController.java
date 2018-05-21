package com.zym.springcloud.eureka.server;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/demo")
public class DemoController {

    @RequestMapping("/test")
    public Map<String,Object> test(){
        Map<String, Object> map = new HashMap<>();
        map.put("zs","ls");
        return map;
    }
}
