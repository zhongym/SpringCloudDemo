package com.zym.springcloud.user.center.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/volatile")
public class VolatileController {

    private boolean is = true;

    @GetMapping("/run")
    public String run() {
        while (is) {
            System.out.println("run");
        }
        return "ok";
    }

    @GetMapping("/switch")
    public String on() {
//        int a = false;
        int b= 'b';
        int d= (byte)1;

        is = !is;
        return "ok";
    }
}
