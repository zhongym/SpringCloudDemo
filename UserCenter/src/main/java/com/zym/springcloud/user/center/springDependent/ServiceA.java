package com.zym.springcloud.user.center.springDependent;

import org.springframework.stereotype.Service;

@Service
public class ServiceA {
    private  ServiceB serviceB;

    private  ServiceC serviceC;

//    public ServiceA(ServiceB serviceB, ServiceC serviceC) {
//        this.serviceB = serviceB;
//        this.serviceC = serviceC;
//    }
}
