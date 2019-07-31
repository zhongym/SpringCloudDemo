package com.zym.springcloud.user.center.jdk.io.bio;

import com.zym.springcloud.user.center.jdk.io.TimeServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhongym
 */
@SuppressWarnings("all")
public class BIOTimeServer implements TimeServer {

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 100, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1024));
    private AtomicInteger idGen = new AtomicInteger();

    @Override
    public void start(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            System.out.println("服务器启动.......");
            while (true) {
                System.out.println("等待客户端接入......");
                Socket cSocket = socket.accept();

                int id = idGen.incrementAndGet();
                String cName = "c" + id;
                System.out.println("接入客户端" + cName);
                executor.submit(new TimeServerHandler(cName, cSocket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    class TimeServerHandler implements Runnable {
        private Socket cSocket;
        private String cName;

        public TimeServerHandler(String cName, Socket cSocket) {
            this.cName = cName;
            this.cSocket = cSocket;
        }

        @Override
        public void run() {
            log("消费");
            try (InputStream in = cSocket.getInputStream(); OutputStream on = cSocket.getOutputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while (true) {
                    String line = reader.readLine();
                    log("输入内容:" + line);
                    if ("time".equals(line)) {
                        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n";
                        on.write(format.getBytes());
                        on.flush();
                    }
                    if ("end".equals(line)) {
                        on.write("结束".getBytes());
                        on.flush();
                        break;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            log("消费完成");
        }

        private void log(String v) {
            System.out.println(Thread.currentThread().getName() + "-----> " + cName + " ---> " + v);
        }
    }

    public static void main(String[] args) {
        new BIOTimeServer().start(800);
    }
}
