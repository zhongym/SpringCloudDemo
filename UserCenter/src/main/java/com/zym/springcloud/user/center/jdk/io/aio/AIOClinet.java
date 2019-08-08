package com.zym.springcloud.user.center.jdk.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AIOClinet {
    private AsynchronousSocketChannel socketChannel;

    private final Object waiter = new Object();

    public void run() {
        try {
            socketChannel = AsynchronousSocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 800), this, new CompletionHandler<Void, AIOClinet>() {
                @Override
                public void completed(Void result, AIOClinet attachment) {

                    ByteBuffer buff = getByteBuffer("time");
                    socketChannel.write(buff, buff, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            CompletionHandler<Integer, ByteBuffer> completionHandler = this;
                            if (attachment.hasRemaining()) {
                                socketChannel.write(attachment, attachment, this);
                            } else {
                                ByteBuffer b = ByteBuffer.allocate(1024);
                                socketChannel.read(b, b, new CompletionHandler<Integer, ByteBuffer>() {
                                    @Override
                                    public void completed(Integer result, ByteBuffer attachment) {
                                        String str = getStr(attachment);
                                        System.out.println("read:" + str + "->" + Thread.currentThread());

                                        if (str.startsWith("当前时间")) {
                                            ByteBuffer buff = getByteBuffer("end");
                                            socketChannel.write(buff, buff, new CompletionHandler<Integer, ByteBuffer>() {
                                                @Override
                                                public void completed(Integer result, ByteBuffer attachment) {
                                                    if (attachment.hasRemaining()) {
                                                        socketChannel.write(attachment, attachment, this);
                                                    } else {
                                                        synchronized (waiter) {
                                                            waiter.notify();
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void failed(Throwable exc, ByteBuffer attachment) {

                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void failed(Throwable exc, ByteBuffer attachment) {
                                        exc.printStackTrace();
                                    }
                                });
                            }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            exc.printStackTrace();
                        }
                    });
                }

                @Override
                public void failed(Throwable exc, AIOClinet attachment) {
                    exc.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        synchronized (waiter) {
            try {
                waiter.wait();
                socketChannel.close();
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


    public static void main(String[] args) throws IOException {
        new AIOClinet().run();
    }
}
