package com.zym.springcloud.user.center.jdk.io.aio;

import com.zym.springcloud.user.center.jdk.io.TimeServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhongym
 */
public class AIOTimeServer implements TimeServer {

    private final Object wait = new Object();
    private AsynchronousServerSocketChannel channel;

    @Override
    public void start(int port) {
        System.out.println("start" + Thread.currentThread().getName());
        try {
            channel = AsynchronousServerSocketChannel.open();
            channel.bind(new InetSocketAddress("127.0.0.1", port));
        } catch (IOException e) {
            e.printStackTrace();
        }
        channel.accept(this, new CompletionHandler<AsynchronousSocketChannel, AIOTimeServer>() {
            @Override
            public void completed(AsynchronousSocketChannel clientChannel, AIOTimeServer attachment) {
                System.out.println("accept" + Thread.currentThread().getName());
                channel.accept(attachment, this);

                ByteBuffer dest = ByteBuffer.allocate(1024);
                clientChannel.read(dest, dest, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        System.out.println("read" + Thread.currentThread().getName());

                        ByteBuffer dest = ByteBuffer.allocate(1024);
                        clientChannel.read(dest, dest, this);

                        String content = getStr(attachment);
                        System.out.println(content);
                        if ("time".equals(content)) {
                            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n";
                            doWrite(clientChannel, "当前时间"+format);
                        }
                        if ("end".equals(content)) {
                            synchronized (wait) {
                                wait.notify();
                            }
                        }
                    }

                    private void doWrite(AsynchronousSocketChannel clientChannel, String format) {
                        ByteBuffer dest = getByteBuffer(format);
                        clientChannel.write(dest, dest, new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                                System.out.println("write" + Thread.currentThread().getName());

                                if (attachment.hasRemaining()) {
                                    clientChannel.write(attachment, attachment, this);
                                }
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {
                                exc.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        exc.printStackTrace();
                    }
                });
            }

            @Override
            public void failed(Throwable exc, AIOTimeServer attachment) {
                exc.printStackTrace();
            }
        });


        synchronized (wait) {
            try {
                System.out.println("等待结束通知");
                wait.wait();
                channel.close();
                System.out.println("结束-----|");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private ByteBuffer getByteBuffer(String s) {
        byte[] bytes = s.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        return buffer;
    }

    private String getStr(ByteBuffer buffer) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return new String(bytes);
    }

    public static void main(String[] args) {
        new AIOTimeServer().start(800);
    }
}
