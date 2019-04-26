package com.zym.springcloud.user.center.jdk.jvm;

import java.io.File;
import java.io.FileInputStream;

public class ClassLoaderDemo {

    static class MyClassLoader extends ClassLoader {
        public MyClassLoader() {
            super(null);
        }

        private String classPath = "E:\\SpringCloudDemo\\UserCenter\\target\\classes\\";

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            return findClass(name);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            String fileName = name.replace('.', '/').concat(".class");

            File file = new File(classPath + fileName);
            try {
                try (FileInputStream stream = new FileInputStream(file)) {
                    byte[] bytes = new byte[stream.available()];
                    stream.read(bytes, 0, bytes.length);
                    return defineClass(name, bytes, 0, bytes.length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.findClass(name);

        }
    }

    /**
     *  -XX:+TraceClassUnloading
     */
    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = myClassLoader.loadClass("java.util.ArrayList");

        System.out.println("");
    }

    private static void unloadClass() throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass2 = myClassLoader.loadClass("com.zym.springcloud.user.center.jdk.jvm.ClassDemo");
        aClass2 = null;
        myClassLoader=null;

        System.gc();

        System.out.println("");
        System.out.println("");

        /**
         * [Unloading class com.zym.springcloud.user.center.jdk.jvm.ClassDemo 0x00000007c0062218]
         */}

    private static void test1() throws ClassNotFoundException {
        Class<ClassDemo> aClass = ClassDemo.class;
        Class<?> aClass1 = ClassLoader.getSystemClassLoader().loadClass("com.zym.springcloud.user.center.jdk" +
                ".ClassDemo");

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass2 = myClassLoader.loadClass("com.zym.springcloud.user.center.jdk.jvm.ClassDemo");


        System.out.println(aClass == aClass1);
        System.out.println(aClass == aClass2);
        System.out.println(aClass1 == aClass2);
    }

    public static void test() throws Exception {
        Class<ClassLoaderDemo> aClass1 = ClassLoaderDemo.class;
        ClassLoader classLoader = aClass1.getClassLoader();

        //应用程序加载器
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> aClass = loader.loadClass("com.zym.springcloud.user.center.jdk.jvm.LooadDemo");
        System.out.println(aClass);

        ClassLoader parent = loader.getParent();

        ClassLoader parent1 = parent.getParent();

        Class<LooadDemo> looadDemoClass = LooadDemo.class;



        Class<?> aClass2 = Class.forName("com.zym.springcloud.user.center.jdk.jvm.LooadDemo");


        MyClassLoader myLoader = new MyClassLoader();
        ClassLoader parent2 = myLoader.getParent();

        Class<?> bClass = myLoader.loadClass("com.zym.springcloud.user.center.jdk.jvm.LooadDemo");
        System.out.println(bClass);

        Class<?> aClass3 = myLoader.loadClass("com.zym.springcloud.activity.center.domain.Activity");
        System.out.println(aClass3);
        //com.zym.springcloud.activity.center.domain.Activity
    }

}
