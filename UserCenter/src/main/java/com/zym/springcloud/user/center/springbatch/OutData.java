package com.zym.springcloud.user.center.springbatch;

public class OutData {
    private Long activityId;
    private String agentCode;
    private String code;
    private String title;

    public OutData(InData inData) {
        this.activityId = inData.getActivityId();
        this.agentCode = inData.getAgentCode();
        this.code = inData.getCode();
        this.title = inData.getTitle();
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
        return "OutData{" +
                "activityId=" + activityId +
                ", agentCode='" + agentCode + '\'' +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
