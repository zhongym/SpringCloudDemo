package com.zym.springcloud.user.center.jdk.generics;

public class Pair<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public static void main(String[] args) {
        Pair x = new Pair<>();
        Object value = x.getValue();
        x.setValue(1);
        System.out.println(x);
    }
}  