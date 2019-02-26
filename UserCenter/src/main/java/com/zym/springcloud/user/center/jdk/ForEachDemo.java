package com.zym.springcloud.user.center.jdk;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ForEachDemo {

    public static void main(String[] args) {
        for (String arg : new A()) {

        }
    }

    static class A implements Iterable<String>{
        @Override
        public void forEach(Consumer<? super String> action) {

        }

        @Override
        public Spliterator<String> spliterator() {
            return null;
        }

        @Override
        public Iterator<String> iterator() {
            return null;
        }
    }
}
