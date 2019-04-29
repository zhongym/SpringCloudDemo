package com.zym.springcloud.user.center.jdk.jvm.javac;

public class JavacDebugDemo {
    private String c = "c";

    {
        c = "CC";
        i = 33;
        System.out.println("{}");
    }

    public JavacDebugDemo(String c) {
        this.c = c;
        System.out.println("JavacDebugDemo(JavacDebugDemo(String c))");

        //编译器编译后生成对应的初始化函数
        //  "<init>":(Ljava/lang/String;)V
        //  super();
        //  private String c = "c";
        //  {
        //      c = "CC";
        //      i = 33;
        //      System.out.println("{}");
        //  }
        //  private int i = 1;
        // this.c = c;
        // System.out.println("JavacDebugDemo(JavacDebugDemo(String c))");
    }

    public JavacDebugDemo(String c, int i) {
        this.c = c;
        this.i = i;
        System.out.println("JavacDebugDemo(String c, int i)");

        //编译器编译后生成对应的初始化函数
        //  "<init>":(Ljava/lang/String;I)V
        //  super();
        //  private String c = "c";
        //  {
        //      c = "CC";
        //      i = 33;
        //      System.out.println("{}");
        //  }
        //  this.c = c;
        //  this.i = i;
        //  System.out.println("JavacDebugDemo(String c, int i)");
    }

    private int i = 1;

    public JavacDebugDemo() {
        c = "aa";
        //编译器编译后生成对应的初始化函数
        //  "<init>":()V
        //  super();
        //  private String c = "c";
        //  {
        //      c = "CC";
        //      i = 33;
        //      System.out.println("{}");
        //  }
        //  c = "aa";
    }

    public static void main(String[] args) {
        JavacDebugDemo demo1 = new JavacDebugDemo("CCC");
    }
}
