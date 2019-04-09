package com.zym.springcloud.user.center.jdk;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

public class IteratorDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){

        }


        list.removeIf((i)->true);

        Spliterator<Integer> spliterator = list.spliterator();
        spliterator.tryAdvance((i)->{
            System.out.println(i);
        });

    }
}
