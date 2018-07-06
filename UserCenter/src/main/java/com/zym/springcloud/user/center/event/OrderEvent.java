package com.zym.springcloud.user.center.event;

import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {
    private Long billId;

    public OrderEvent(Long billId) {
        super(billId);
        this.billId = billId;
    }

    public Long getBillId() {
        return billId;
    }
}
