package com.zym.springcloud.user.center.jdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableDemo {
    public static void main(String[] args) throws Exception {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance);

        final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(instance);

        final ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
        final Object o = inputStream.readObject();
        System.out.println(o);

        if (instance != o) {
            System.out.println("不同对象");
        }

        final EnumDemo autumn = EnumDemo.AUTUMN;

        final ByteArrayOutputStream byteOut1 = new ByteArrayOutputStream();
        ObjectOutputStream out1 = new ObjectOutputStream(byteOut1);
        out1.writeObject(autumn);

        final ObjectInputStream inputStream1 = new ObjectInputStream(new ByteArrayInputStream(byteOut1.toByteArray()));
        final Object o1 = inputStream1.readObject();
        System.out.println(o1);

        if (autumn != o1) {
            System.out.println("不同对象");
        }

    }
}
