package com.zym.springcloud.user.center.activityCenter;

import com.zym.springcloud.user.center.activityCenter.domain.Activity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhong on 2017/9/5.
 */

@FeignClient("activityCenter")
@RequestMapping("/api/activity")
public interface ActivityService {

    @GetMapping("/{activityId}")
    Activity get(@PathVariable("activityId") Long activityId);

}
