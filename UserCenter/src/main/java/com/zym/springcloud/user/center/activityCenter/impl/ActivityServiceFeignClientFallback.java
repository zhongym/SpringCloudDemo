package com.zym.springcloud.user.center.activityCenter.impl;

import com.zym.springcloud.user.center.activityCenter.domain.Activity;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.LongAdder;

@Component
public class ActivityServiceFeignClientFallback implements ActivityServiceFeignClient {

    private LongAdder longAdder = new LongAdder();

    @Override
    public Activity getByActivityId(Long activityId) {

        longAdder.add(1);
        System.out.println("快速错误，降级处理->:" + longAdder.sum());

        Activity activity = new Activity();
        activity.setName("本地生成");
        return activity;
    }
}
