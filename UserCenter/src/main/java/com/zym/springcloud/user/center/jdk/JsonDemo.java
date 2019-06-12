package com.zym.springcloud.user.center.jdk;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonDemo {

    Integer a;


    public static void main(String[] args) throws IOException {

        ObjectMapper om = new ObjectMapper();
        JsonDemo jsonDemo = om.readValue("{\"a\":\"\"}", JsonDemo.class);
        System.out.println(jsonDemo);
    }


    public Integer getA() {
        return a;
    }

    public synchronized void setA(Integer a) {
        this.a = a;
    }
}
