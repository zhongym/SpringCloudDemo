package com.zym.springcloud.user.center.activityCenter.impl;

import com.zym.springcloud.user.center.activityCenter.domain.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityServiceFeignClientFallback implements ActivityServiceFeignClient {
    @Override
    public Activity getByActivityId(Long activityId) {
        Activity activity = new Activity();
        activity.setName("本地生成");
        return activity;
    }
}
