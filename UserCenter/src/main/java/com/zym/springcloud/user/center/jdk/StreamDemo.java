package com.zym.springcloud.user.center.jdk;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("0" ,"1", "3");
        list.stream()
//                .map(str -> Integer.valueOf(str))
//                .filter(i -> i > 0)
                .reduce(1L,
                        (Long l, String i) -> {
                            return l + Long.valueOf(i);
                        },
                        (Long l, Long i) -> {
                            return l + i;
                        }
                );
//                .reduce(0, (i1, i2) -> {
//                  return   i1 + i2;
//                });
//
//                .reduce((i1, i2) -> {
//                    return i1 + i2;
//                });

//        System.out.println(reduce);

    }
}
