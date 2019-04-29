package com.zym.springcloud.user.center.jdk.jvm.javac;

public class BoxDemo {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        Integer a = 1;  // Integer a = Integer.valueOf(1)
        Integer b = 2;  // Integer a = Integer.valueOf(2)

        Integer c = 3;  // Integer a = Integer.valueOf(3)
        Integer d = 3;  // Integer a = Integer.valueOf(3)

        Integer e = 321;  // Integer a = Integer.valueOf(321)
        Integer f = 321;  // Integer a = Integer.valueOf(321)
        Long g = 3L;      // Long g = Long.valueOf(3L)

        System.out.println(c == d); //true 因为 -128 -> 127 有缓存
        System.out.println(e == f); //false 没有缓存  new Integer(i)
        System.out.println(c == (a + b)); //true   c.intValue:() == (a.intValue()+b.intValue())
        System.out.println(c.equals(a + b)); //true  c.equals(Integer.valueOf(a.intValue()+b.intValue()))
        System.out.println(g == (a + b));    //true  g.longValue()== (long)(a.intValue()+b.intValue())  等同 System.out.println(3L == (long) (1 + 2));
        System.out.println(g.equals(a + b)); //false  g.equals(Integer.valueOf(a.intValue()+b.intValue()))
    }
}
