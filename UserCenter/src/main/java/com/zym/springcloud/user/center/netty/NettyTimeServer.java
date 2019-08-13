package com.zym.springcloud.user.center.netty;

import com.zym.springcloud.user.center.jdk.io.TimeServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NettyTimeServer implements TimeServer {
    private Channel channel;

    @Override
    public void start(int port) {
        NioEventLoopGroup pGroup = new NioEventLoopGroup();
        NioEventLoopGroup cGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(pGroup, cGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //解决tcp粘包和折包
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast(new StringDecoder());

                        ch.pipeline().addLast(new ChannelHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                String content = getStr(msg);
                                out(content);
                                if ("time".equals(content)) {
                                    String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n";
                                    ctx.write(getByteBuffer(format));
                                }
                                if ("end".equals(content)) {
                                    channel.close();
                                }
                                if ("close".equals(content)) {
                                    ctx.channel().close();
                                }
                            }

                            @Override
                            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                ctx.flush();
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                ctx.close();
                            }
                        });
                    }
                });
        try {
            ChannelFuture future = bootstrap.bind(new InetSocketAddress("127.0.0.1", port)).sync();

            channel = future.channel();
            channel.closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pGroup.shutdownGracefully();
            cGroup.shutdownGracefully();
        }

    }

    private void out(String s) {
        System.out.println(Thread.currentThread().getName() + "::" + s);
    }


    private ByteBuf getByteBuffer(String s) {
        return Unpooled.copiedBuffer(s.getBytes());
    }

    private String getStr(Object buffer) {
        if (buffer instanceof String) {
            return (String) buffer;
        }

        if (buffer instanceof ByteBuf) {
            ByteBuf buffer_ = (ByteBuf) buffer;
            byte[] bytes = new byte[buffer_.readableBytes()];
            buffer_.readBytes(bytes);
            return new String(bytes);
        }
        throw new RuntimeException("无效类型:" + buffer.getClass());
    }

    public static void main(String[] args) {
        new NettyTimeServer().start(800);
    }
}
