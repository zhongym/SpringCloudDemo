package com.zym.springcloud.activity.center.controller;

import com.zym.springcloud.activity.center.domain.Activity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2017/9/5.
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Value("${server.port}")
    private Long port;
    @GetMapping("/{activityId}")
    public Activity get(@PathVariable("activityId") Long activityId) {
        Activity activity = new Activity();
        activity.setActivityId(port);
        activity.setName("活动名称");
        activity.setCode("zs-12");
        return activity;
    }
}
