package com.zym.springcloud.user.center.jdk.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
/**
 * @author zhongym
 */
public class NIOTimeClient {

    public static void main(String[] args) {
        try (SocketChannel cChannel = SocketChannel.open();
             Selector selector = Selector.open()) {
            cChannel.configureBlocking(false);
            if (cChannel.connect(new InetSocketAddress("127.0.0.1", 800))) {
                cChannel.register(selector, SelectionKey.OP_READ);
                doWrite(cChannel, "time");
            } else {
                cChannel.register(selector, SelectionKey.OP_CONNECT);
            }

            while (true) {
                selector.select();
                for (SelectionKey selectedKey : selector.selectedKeys()) {
                    handler(selectedKey, selector);
                }
                selector.selectedKeys().clear();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handler(SelectionKey selectedKey, Selector selector) {
        if (!selectedKey.isValid()) {
            return;
        }

        try {
            SocketChannel cChannel = (SocketChannel) selectedKey.channel();

            if (selectedKey.isConnectable()) {
                if (cChannel.finishConnect()) {
                    cChannel.register(selector, SelectionKey.OP_READ);
                    doWrite(cChannel, "time");
                } else {
                    throw new RuntimeException("连接失败");
                }
            }

            if (selectedKey.isReadable()) {
                ByteBuffer dst = ByteBuffer.allocate(1024);
                int read = cChannel.read(dst);
                if (read > 0) {
                    dst.flip();
                    byte[] dest = new byte[dst.remaining()];
                    dst.get(dest);
                    String content = new String(dest);
                    System.out.println(content);
                    if ("结束".equals(content)) {
                        System.exit(0);
                    } else {

                        doWrite(cChannel, "end");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void doWrite(SocketChannel cChannel, String v) {
        byte[] bytes = v.getBytes();
        ByteBuffer src = ByteBuffer.allocate(bytes.length);
        src.put(bytes);
        src.flip();
        try {
            cChannel.write(src);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
