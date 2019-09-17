package com.zym.springcloud.user.center.netty.businessThreadPool;

import com.zym.springcloud.user.center.jdk.io.TimeServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskQueueDemo implements TimeServer {

    private Channel channel;

    @Override
    public void start(int port) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        //业务线程池
        ExecutorService busThreadPool = Executors.newFixedThreadPool(10);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
//                .option(ChannelOption.SO_BACKLOG, 1024)
//                .childOption(ChannelOption.SO_SNDBUF, 100)
//                .childOption(ChannelOption.SO_RCVBUF, 1000)
//                .childOption(ChannelOption.TCP_NODELAY, true)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //解决tcp粘包和折包
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast(new StringDecoder(){
                            @Override
                            protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
                                super.decode(ctx, msg, out);
                                System.out.println("decoder:" + Thread.currentThread());
                            }
                        });
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new MessageToMessageDecoder<String>() {
                            @Override
                            protected void decode(ChannelHandlerContext ctx, String content, List<Object> out) throws Exception {
                                System.out.println("decode:" + Thread.currentThread());
                                busThreadPool.execute(() -> {
                                    System.out.println("execute:" + Thread.currentThread() + ":" + content);
                                    if ("time".equals(content)) {
                                        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n";
                                        ctx.writeAndFlush(format);
                                    }
                                    if ("end".equals(content)) {
                                        channel.close();
                                    }
                                    if ("close".equals(content)) {
                                        ctx.channel().close();
                                    }
                                });
                            }
                        });
                    }
                });

        ChannelFuture future = bootstrap.bind(new InetSocketAddress("127.0.0.1", port));
        channel = future.channel();
        try {
            channel.closeFuture()
                    .sync()
                    .addListener(future1 -> {
                        boss.shutdownGracefully();
                        work.shutdownGracefully();
                        busThreadPool.shutdownNow();
                    });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TaskQueueDemo().start(800);
    }
}
