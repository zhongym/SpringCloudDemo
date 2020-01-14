package com.zym.springcloud.user.center.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDemo {

    private int i = 0;

    @Scheduled(fixedDelay = 1000)
    public void partSyncYjpProduct() {
        if (i++ > 10) {
            throw new RuntimeException("");
        }
        System.out.println("---------------------------");
    }
}
