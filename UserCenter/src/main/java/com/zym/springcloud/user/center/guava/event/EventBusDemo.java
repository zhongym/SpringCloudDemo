package com.zym.springcloud.user.center.guava.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;

public class EventBusDemo {
    public static void main(String[] args) throws InterruptedException {
        EventBus eventBus = new AsyncEventBus("bus", Executors.newFixedThreadPool(5));

        eventBus.register(new AListener());
        eventBus.register(new BListener());

        Event event = new Event("eventName");
        eventBus.post(event);

        Thread.sleep(5000);
    }
}
