package com.zym.springcloud.user.center.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Demo implements IDemo{

    public static void main(String[] args) {
        Demo demo = new Demo();
        IDemo o = (IDemo) Proxy.newProxyInstance(Demo.class.getClassLoader(), Demo.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                method.invoke(demo, args);
                return null;
            }
        });

        System.out.println(o);
    }

    @Override
    public void say() {
        System.out.println("say()");
    }
}
