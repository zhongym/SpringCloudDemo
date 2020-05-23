package com.zym.springcloud.user.center.activityCenter.impl;

import com.zym.springcloud.user.center.activityCenter.ActivityService;
import com.zym.springcloud.user.center.activityCenter.domain.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

//@Service
public class ActivityServiceRibbonClient implements ActivityService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Activity getByActivityId(Long activityId) {
        return restTemplate.getForObject("http://activityCenter/api/activity/" + activityId, Activity.class);
    }

    private Activity getByActivityIdForError(Long activityId) {
        Activity activity = new Activity();
        activity.setName("本地生成");
        return activity;
    }

}
