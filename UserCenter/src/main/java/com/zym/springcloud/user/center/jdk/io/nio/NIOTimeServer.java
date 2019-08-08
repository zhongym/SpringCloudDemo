package com.zym.springcloud.user.center.jdk.io.nio;

import com.zym.springcloud.user.center.jdk.io.TimeServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * @author zhongym
 */
public class NIOTimeServer implements TimeServer {

    @Override
    public void start(int port) {

        try (ServerSocketChannel channel = ServerSocketChannel.open();
             Selector selector = Selector.open()) {
            //设置成非阻塞
            channel.configureBlocking(false);
            //绑定端口ip
            channel.bind(new InetSocketAddress("127.0.0.1", port));
            channel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                //就绪列表
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    handler(selector, selectionKey);
                }
                //清空已经处理完的
                selectionKeys.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void handler(Selector selector, SelectionKey selectionKey) {
        if (!selectionKey.isValid()) {
            return;
        }
        if (selectionKey.isAcceptable()) {
            System.out.println("处理接入客户端接入");
            ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
            try {
                //接受客户端，注册到selector上
                SocketChannel clientChannel = serverChannel.accept();
                //设置为非阻塞
                clientChannel.configureBlocking(false);
                clientChannel.register(selector, SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (selectionKey.isReadable()) {
            System.out.println("处理接入客户端读取");
            SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
            try {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int read = clientChannel.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    byte[] dest = new byte[buffer.remaining()];
                    buffer.get(dest);
                    String content = new String(dest);
                    System.out.println("完整输入" + content);
                    if ("time".equals(content)) {
                        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n";
                        doWrite(clientChannel, format);
                    } else if ("end".equals(content)) {
                        doWrite(clientChannel, "结束");
                        //取消监控
                        selectionKey.cancel();
                        //关闭客户端
                        clientChannel.close();
                    }

                } else {
                    System.out.println("无效输入");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doWrite(SocketChannel clientChannel, String format) {
        try {
            byte[] bytes = format.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            clientChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        new NIOTimeServer().start(800);

        Calendar cale = Calendar.getInstance();
        Date time1 = cale.getTime();

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1));
        cale.set(Calendar.DAY_OF_MONTH, 1);
        Date time = cale.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
    }

}
