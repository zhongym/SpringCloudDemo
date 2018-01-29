package com.zym.springcloud.user.center.controller;

import com.zym.springcloud.user.center.activityCenter.ActivityService;
import com.zym.springcloud.user.center.activityCenter.domain.Activity;
import com.zym.springcloud.user.center.activityCenter.impl.ActivityServiceFeignClient;
import com.zym.springcloud.user.center.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhong on 2017/9/5.
 */
@RestController
@RequestMapping("/api/user/activity/")
public class UserActivityController {

    @Qualifier("activityServiceRibbonClient")
    @Autowired
    private ActivityService activityServiceRibbonClient;

    @Autowired
    private ActivityServiceFeignClient activityServiceFeignClient;

    @RequestMapping("/get")
    public Map<String, Object> getUserActivity(String type) {

        Activity activity = null;
        if ("ribbon".equals(type)) {
            activity = activityServiceRibbonClient.getByActivityId(123L);
        } else {
            activity = activityServiceFeignClient.getByActivityId(123L);
        }
        User user = new User();
        user.setUserId(12L);

        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("activity", activity);
        return map;
    }
}
