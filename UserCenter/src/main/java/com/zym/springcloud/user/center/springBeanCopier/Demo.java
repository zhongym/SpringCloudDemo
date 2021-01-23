package com.zym.springcloud.user.center.springBeanCopier;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        A a = new A();
        a.setName("adfasfd");
        a.setL(1L);
        a.setI(1);
        a.setS("3");
        a.setGmtCreate(LocalDateTime.now());
        a.setGmtUpdate(new Date());
        a.setGmtM(LocalDateTime.now());
        a.setGmtS("2021-01-22 09:34:13");

        a.setDate(new Date());
        a.setDateS("2021-01-22 09:34:13");

        a.setDate1(LocalDate.now());

        A a1 = new A();
        a1.setName("adfsafdfsf");
        a.setA(a1);

        B b = MBeanUtils.copy(a, new B(), true);

        String s = b.getStrings().get(0);
        System.out.println(b);


    }

    @Data
    public static class A {
        private String name;
        private Long l;
        private Integer i;
        private String s;

        private LocalDateTime gmtCreate;
        private Date gmtUpdate;

        private LocalDateTime gmtM;
        private String gmtS;

        private Date date;
        private String dateS;

        private LocalDate date1;

        private A a;

        private List<Long> longs = new ArrayList<Long>(){
            {
                add(1L);
                add(3L);
            }
        };

        private List<Long> strings= new ArrayList<Long>(){
            {
                add(2L);
                add(3L);
            }
        };

        private List<Long> stringss= new ArrayList<Long>(){
            {
                add(3L);
                add(3L);
            }
        };
    }

    @Data
    public static class B {
        private String name;
        private Long l;
        private String i;
        private Integer s;

        private Date gmtCreate;
        private LocalDateTime gmtUpdate;

        private String gmtM;
        private LocalDateTime gmtS;

        private String date;
        private Date dateS;

        private String date1;

        private B a;

        private List<Long> longs;
        private List<String> strings;
        private String stringss;

    }
}
