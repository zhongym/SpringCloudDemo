package com.zym.springcloud.user.center.activityCenter.impl;

import com.zym.springcloud.user.center.activityCenter.domain.Activity;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.LongAdder;

/**
 * Created by zhong on 2017/9/5.
 */

@FeignClient(value = "activityCenter",
        fallbackFactory = ActivityServiceFeignClient.ActivityServiceFallbackFactory.class
/*fallback = ActivityServiceFeignClientFallback.class*/
)
@RequestMapping("/api/activity")
public interface ActivityServiceFeignClient {

    @GetMapping("/{activityId}")
    Activity getByActivityId(@PathVariable("activityId") Long activityId);


    @Component
    class ActivityServiceFallbackFactory implements FallbackFactory {

//        @Autowired
//        private ActivityServiceFeignClientFallback activityServiceFeignClientFallback;

        private LongAdder longAdder = new LongAdder();

        @Override
        public Object create(Throwable cause) {

            return new ActivityServiceFeignClient() {

                @Override
                public Activity getByActivityId(Long activityId) {

                    cause.printStackTrace();

                    longAdder.add(1);
                    System.out.println("快速错误，降级处理->:" + longAdder.sum());

                    Activity activity = new Activity();
                    activity.setName("本地生成");
                    return activity;
                }
            };
        }
    }


}
