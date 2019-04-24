package com.zym.springcloud.user.center.jdk.jvm;

public class LooadDemo {
    public static int A = 10;
    static {
        LooadDemo looadDemo = new LooadDemo();
        System.out.println(looadDemo.A); //输出10

        A = 20;
        System.out.println(looadDemo.A); //输出20

        System.out.println(looadDemo.B); //输出0

        System.out.println(LooadDemo.B); //输出0
    }

    private static int B = 20;

    /**
     * <clinit>()方法是编译器根据java源文件中定义的所有类变量的赋值动作和静态代码块按顺序合并产生的
     *
     *<clinit>(){
     *
     *       private static int A = 10;
     *       static {
     *         LooadDemo looadDemo = new LooadDemo();
     *         System.out.println(looadDemo.A);
     *
     *         A = 20;
     *         System.out.println(looadDemo.A);
     *
     *         System.out.println(looadDemo.B);
     *     }
     *     private static int B = 20;
     *}
     *
     *
     */
    public static void main(String[] args) {


    }

}
