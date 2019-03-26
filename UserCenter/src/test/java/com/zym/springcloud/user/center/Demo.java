package com.zym.springcloud.user.center;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.StringJoiner;

public class Demo {

    private Integer age;
    private String name;


    public static void main(String[] args) {
        Demo d = new Demo();
        d.age = 1;
        d.name = "zz";
        System.out.println(d);
    }
}
