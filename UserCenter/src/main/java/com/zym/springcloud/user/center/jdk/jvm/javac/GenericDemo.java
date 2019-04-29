package com.zym.springcloud.user.center.jdk.jvm.javac;

import com.zym.springcloud.user.center.activityCenter.domain.Activity;

import java.util.ArrayList;
import java.util.List;

interface I<T> {
    T get();

    List<T> findAll();

    void save(T t);

    void saves(List<T> ts);
}

public class GenericDemo implements I<Activity> {
    @Override
    public Activity get() {
        return new Activity();
    }

    @Override
    public List<Activity> findAll() {
        List<Activity> list = new ArrayList<>();
        list.add(null);
        list.add(new Activity());
        return list;
    }

    @Override
    public void save(Activity activity) {
        activity.setActivityId(1L);
        System.out.println(activity.getActivityId());
    }

    @Override
    public void saves(List<Activity> activities) {
        for (Activity activity : activities) {
            System.out.println(activity.getActivityId());
        }
    }

    public static void main(String[] args) {
        GenericDemo demo = new GenericDemo();
        Activity activity = demo.get();
    }
}
