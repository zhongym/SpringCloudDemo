package com.zhongym.securitydemo.controller;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Data
    public static class Hello {
        private Long id;
        private String name;
    }

    @GetMapping("/hello")
    public Hello hello(String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);


        Hello hello = new Hello();
        hello.name = name;
        hello.id = 123L;
        return hello;
    }
}
