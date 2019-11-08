package com.zym.springcloud.user.center.springDependent;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn({"serviceB","serviceC"})
public class ServiceA {
    private final ServiceB serviceB;

    private final ServiceC serviceC;

    public ServiceA(ServiceB serviceB, ServiceC serviceC) {
        this.serviceB = serviceB;
        this.serviceC = serviceC;
    }
}
