package com.zym.springcloud.user.center.orika;

import java.time.LocalDateTime;
import java.util.Date;

public class DTO2 {
    private int i;
    private Integer integer;
    private String string;
    private LocalDateTime dateTime;
    private Date date;
    private DTO2 dto;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DTO2 getDto() {
        return dto;
    }

    public void setDto(DTO2 dto) {
        this.dto = dto;
    }
}
