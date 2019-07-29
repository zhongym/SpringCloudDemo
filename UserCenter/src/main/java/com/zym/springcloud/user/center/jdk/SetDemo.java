package com.zym.springcloud.user.center.jdk;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class SetDemo {

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(2);
        set.add(1);
        set.add(5);
        set.add(9);

        List<String> collect = set.stream().map(id -> "id:" + id).collect(Collectors.toList());

        System.out.println(set);
        System.out.println(collect);
    }
}
