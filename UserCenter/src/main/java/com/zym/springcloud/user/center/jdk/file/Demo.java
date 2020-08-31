package com.zym.springcloud.user.center.jdk.file;

import java.io.*;

public class Demo {
    public static void main(String[] args) throws IOException {

        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("a.txt"));
        System.setOut(new PrintStream(out));

//        for (int i = 0; i < 1000000; i++) {
            System.out.println("adfsafdsa");
//        }
        out.flush();
        out.close();

    }
}
