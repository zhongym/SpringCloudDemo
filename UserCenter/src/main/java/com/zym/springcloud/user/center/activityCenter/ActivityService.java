package com.zym.springcloud.user.center.activityCenter;

import com.zym.springcloud.user.center.activityCenter.domain.Activity;

public interface ActivityService {

    Activity getByActivityId(Long activityId);

}
