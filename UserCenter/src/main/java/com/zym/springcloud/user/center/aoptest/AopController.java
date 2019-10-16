package com.zym.springcloud.user.center.aoptest;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Aspect
@RestController
@RequestMapping("/api/aop")
public class AopController {

    @Autowired
    private AopService aopService;

    @GetMapping("async")
    public String async() {
        aopService.async();
        System.out.println(Thread.currentThread() + "AopController");
        return "ok";
    }
}
