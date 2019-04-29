package com.zym.springcloud.user.center.jdk.jvm;

import java.lang.invoke.MethodHandle;

import static java.lang.invoke.MethodHandles.lookup;
import static java.lang.invoke.MethodType.methodType;

public class MethodHandleDemo {

    class GrandFather {
        void say() {
            System.out.println(" i am GrandFather1");
        }
    }

    class Father extends GrandFather {
        @Override
        void say() {
            System.out.println(" i am Father1");
        }
    }

    class Son extends Father {
        @Override
        void say() {
            //在这里面，调用Grand的方法
            try {

                // access to super and self methods via invokeSpecial:
                MethodHandle mh = lookup().findSpecial(GrandFather.class, "say",
                        methodType(void.class), getClass());

//                MethodHandle MH_super = MethodHandles.lookup().findSpecial(Father.class, "say",
//                        methodType(void.class), getClass());

                mh.invoke(this);
//                MH_super.invoke(this);

            } catch (Throwable e) {
                e.printStackTrace();
            }

        }
    }
//
//    static Lookup lookup() {
//        return MethodHandles.lookup();
//    }
//
//
//    public void test() throws Throwable {
//
//        //调用父类的方法
//        new Son().say();
//
//        // no access to constructor via invokeSpecial:
////        MethodHandle MH_newListie = MethodHandleDemo.lookup().findConstructor(Son.class, methodType(void.class));
////        Son l = (Son) MH_newListie.invokeExact();
//        try {
//            MethodHandle special = MethodHandleDemo.lookup().findSpecial(Son.class, "<init>", methodType(void.class), Son.class);
//        } catch (Exception e) {
//            //报错，
//
//        }
//
//
//        //findStatic 获取类的静态方法
//        MethodHandle asList = lookup().findStatic(Arrays.class, "asList", methodType(List.class, Object[].class));
//        List<String> invoke = (List<String>) asList.invoke("str", "str2");
//        System.out.println(invoke);
//
//    }

    public static void main(String[] args) throws Throwable {
//        new MethodHandleDemo().test();

        (new MethodHandleDemo().new Son()).say();
    }

}

