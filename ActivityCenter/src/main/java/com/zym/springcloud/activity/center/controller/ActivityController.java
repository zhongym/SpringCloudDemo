package com.zym.springcloud.activity.center.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zym.springcloud.activity.center.domain.Activity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by zhong on 2017/9/5.
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private boolean ifThrow = true;

    private LongAdder longAdder = new LongAdder();

    @GetMapping("/switch")
    public String switchON() {
        ifThrow = !ifThrow;
        return "ok";
    }

    private int lockSize = 50;

    private List<Integer> lockList = new ArrayList<>(lockSize);

    {
        for (int i = 0; i < lockSize; i++) {
            lockList.add(Integer.valueOf(i));
        }
    }

    @GetMapping("/{activityId}")
    public Activity getByActivityId(@PathVariable("activityId") Long activityId) {
        Activity activity = new Activity();
        activity.setActivityId(activityId);
        activity.setName("活动名称");
        activity.setCode("zs-12");

        longAdder.add(1);
        int sum = (int) longAdder.sum();
        System.out.println("请求次数->:" + sum);

//        if (ifThrow) {
//            throw new RuntimeException("");
//        }
//
//        int i = sum % lockSize;
//
//        synchronized (lockList.get(i)) {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        return activity;
    }

    @GetMapping("/test")
    public Activity test(Page p, Vo v) {
        return null;
    }

    public static class Page{
        private int page;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }
    }

    public static class Vo{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
