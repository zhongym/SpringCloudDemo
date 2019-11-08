package com.zym.springcloud.user.center.guava.event;

import com.google.common.eventbus.Subscribe;

public class AListener {

    @Subscribe
    public void listener(Event event) {
        System.out.println(Thread.currentThread().getName() + "->AListener->" + event.name);
    }
}
