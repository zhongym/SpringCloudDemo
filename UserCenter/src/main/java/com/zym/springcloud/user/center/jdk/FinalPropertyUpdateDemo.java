package com.zym.springcloud.user.center.jdk;

import java.lang.reflect.Field;

/**
 * @author zhongym
 */
public class FinalPropertyUpdateDemo {

//        private final int a;
    private final int a = get();//(null != null ? 0 : 1);

    private int get() {
        return 0;
    }

    public FinalPropertyUpdateDemo() {
//        a = 0;
    }

    public int getA() {
        return a;
    }


    public static void main(String[] args) throws Exception {
        String a = "ab";
        System.out.println(a.getBytes());
        Field value = a.getClass().getDeclaredField("value");
        value.setAccessible(true);
        char[] o = (char[]) value.get(a);

        char[] n = {'c', 'd'};
        value.set(a, n);
        System.out.println(a);
        //ab
        //cd

        FinalPropertyUpdateDemo stringDemo = new FinalPropertyUpdateDemo();
        System.out.println(stringDemo.a);
        Field a1 = stringDemo.getClass().getDeclaredField("a");
        a1.setAccessible(true);

        a1.set(stringDemo, 2);

        System.out.println(stringDemo.getA());


    }

}
