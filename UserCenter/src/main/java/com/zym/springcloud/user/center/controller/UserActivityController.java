package com.zym.springcloud.user.center.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zym.springcloud.user.center.activityCenter.domain.Activity;
import com.zym.springcloud.user.center.activityCenter.impl.ActivityServiceFeignClient;
import com.zym.springcloud.user.center.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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

    /**
     * 多线程获取容器 线程池
     */
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            300, 500,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(60),
            new ThreadFactoryBuilder().setNameFormat("task-thread-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                    super.rejectedExecution(r, e);
                }
            });

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

    @GetMapping("/multiThreadAdd")
    public Map<String, Object> multiThreadAdd() {

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();

        long s = System.currentTimeMillis();

        int count = 300;
        List<CompletableFuture> taskList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            taskList.add(
                    CompletableFuture.runAsync(() -> {
                        RequestContextHolder.setRequestAttributes(requestAttributes);

                        for (int j = 0; j < 100; j++) {

                            RequestScopeCache.get("longAdd", LongAdder.class, () -> {
                                System.out.println("new LongAdder()");
                                return new LongAdder();
                            }).add(1);
                        }

                        RequestContextHolder.resetRequestAttributes();
                    }, executor));

        }

        CompletableFuture.allOf(taskList.toArray(new CompletableFuture[0])).join();
        long e = System.currentTimeMillis();

        System.out.println("用时ms:" + (e - s));

        System.out.println("最终结果:" + RequestScopeCache.get("longAdd", LongAdder.class, LongAdder::new).sum());
        return new HashMap<>();
    }
}
