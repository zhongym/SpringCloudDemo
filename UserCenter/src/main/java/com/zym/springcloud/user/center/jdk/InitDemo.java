package com.zym.springcloud.user.center.jdk;

public class InitDemo {

    private int a = 22;

    {
        System.out.println("{}");
        System.out.println("{} a=" + a);
//        System.out.println("{} b="+b); // 报错，访问不了
        if (1 == 1) {
            try {
                throw new RuntimeException("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public InitDemo() {
        System.out.println("InitDemo()");
        System.out.println("InitDemo() a=" + a);
        System.out.println("InitDemo() b=" + b);
        throw new RuntimeException("");
    }

    private int b = 23;


    public static void main(String[] args) {
        InitDemo initDemo = new InitDemo();

        /**
         * 输出：
         * {}
         * java.lang.RuntimeException:
         * 	at com.zym.springcloud.user.center.jdk.InitDemo.<init>(InitDemo.java:10)
         * 	at com.zym.springcloud.user.center.jdk.InitDemo.main(InitDemo.java:23)
         * InitDemo()
         * Exception in thread "main" java.lang.RuntimeException:
         * 	at com.zym.springcloud.user.center.jdk.InitDemo.<init>(InitDemo.java:19)
         * 	at com.zym.springcloud.user.center.jdk.InitDemo.main(InitDemo.java:23)
         */

        //todo 证明 InitDemo.<init>() 方法组合了成员字段初始化赋值 + {} + 构造方法


    }
}
