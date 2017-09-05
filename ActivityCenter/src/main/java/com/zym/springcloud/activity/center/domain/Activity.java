package com.zym.springcloud.activity.center.domain;

import java.io.Serializable;

/**
 * Created by zhong on 2017/9/5.
 */
public class Activity implements Serializable {
    private Long activityId;
    private String name;
    private String code;
    private String activityType;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
