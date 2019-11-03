package com.zym.springcloud.user.center.springbatch;

public class InData {
    private Long activityId;
    private String agentCode;
    private String code;
    private String title;

    public InData(Long activityId, String agentCode, String code, String title) {
        this.activityId = activityId;
        this.agentCode = agentCode;
        this.code = code;
        this.title = title;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "InData{" +
                "activityId=" + activityId +
                ", agentCode='" + agentCode + '\'' +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
