package com.zym.springcloud.user.center.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener implements ApplicationListener<OrderEvent> {
    @Override
    public void onApplicationEvent(OrderEvent event) {
        System.out.println("下订单" + event.getBillId());
    }
}
