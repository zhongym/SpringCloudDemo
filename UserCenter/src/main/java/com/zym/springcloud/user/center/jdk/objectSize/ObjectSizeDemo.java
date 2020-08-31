package com.zym.springcloud.user.center.jdk.objectSize;

import com.google.common.collect.Lists;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

public class ObjectSizeDemo {

    public static void main(String[] args) {
        List<Long> longs = Lists.newArrayList(1L,2L,3L);

        System.out.println(ClassLayout.parseClass(ArrayList.class).toPrintable());
        System.out.println(ClassLayout.parseClass(Long.class).toPrintable());
        System.out.println(ClassLayout.parseInstance(longs).toPrintable());
        System.out.println(ClassLayout.parseInstance(new ArrayList<>()).toPrintable());
        System.out.println(ClassLayout.parseInstance(longs).instanceSize());
    }
}
