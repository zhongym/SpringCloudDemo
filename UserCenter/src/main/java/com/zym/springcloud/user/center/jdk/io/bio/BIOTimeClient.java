package com.zym.springcloud.user.center.jdk.io.bio;

import java.io.*;
import java.net.Socket;

public class BIOTimeClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 800);
             OutputStream out = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.write("error\n".getBytes());
            out.flush();

            out.write("time\n".getBytes());
            out.flush();

            System.out.println(reader.readLine());

            out.write("end\n".getBytes());
            out.flush();
            System.out.println(reader.readLine());

        } catch (Exception e) {

        }
    }
}
