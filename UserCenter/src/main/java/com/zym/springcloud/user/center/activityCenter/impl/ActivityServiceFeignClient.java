package com.zym.springcloud.user.center.activityCenter.impl;

import com.zym.springcloud.user.center.activityCenter.domain.Activity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhong on 2017/9/5.
 */

@FeignClient(value = "activityCenter", fallback = ActivityServiceFeignClientFallback.class)
@RequestMapping("/api/activity")
public interface ActivityServiceFeignClient {

    @GetMapping("/{activityId}")
    Activity getByActivityId(@PathVariable("activityId") Long activityId);

}
