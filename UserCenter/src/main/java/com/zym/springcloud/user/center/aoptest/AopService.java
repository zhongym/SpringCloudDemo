package com.zym.springcloud.user.center.aoptest;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AopService {

    @Async
    public void async() {
        System.out.println(Thread.currentThread() + " run");
    }
}
