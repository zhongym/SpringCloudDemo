package com.zym.springcloud.user.center.controller;

import com.zym.springcloud.user.center.activityCenter.domain.Activity;
import com.zym.springcloud.user.center.activityCenter.impl.ActivityServiceFeignClient;
import com.zym.springcloud.user.center.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by zhong on 2017/9/5.
 */
@RestController
@RequestMapping("/api/user/activity/")
public class UserActivityController {

//    @Qualifier("activityServiceRibbonClient")
//    @Autowired
//    private ActivityService activityServiceRibbonClient;

    private LongAdder longAdder = new LongAdder();

    @Autowired
    private ActivityServiceFeignClient activityServiceFeignClient;

    @RequestMapping("/get")
    public Map<String, Object> getUserActivity(String type) {


        longAdder.add(1);
        System.out.println("请求次数->:" + longAdder.sum());

        Activity activity = activityServiceFeignClient.getByActivityId(123L);
        User user = new User();
        user.setUserId(12L);

        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("activity", activity);
        return map;
    }

    @RequestMapping("/getLong")
    public Map<String, Object> getUserActivity(@RequestParam("type") Long type) {
        return new HashMap<>();
    }

    @RequestMapping("/getLong1")
    public Map<String, Object> getUserActivity1(Long type) {
        return new HashMap<>();
    }
}
