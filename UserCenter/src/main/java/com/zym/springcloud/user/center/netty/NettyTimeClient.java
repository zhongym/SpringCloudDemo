package com.zym.springcloud.user.center.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class NettyTimeClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
//                .option(ChannelOption.TCP_NODELAY, true)
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 100)
//                .option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 100)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast(new ChannelHandlerAdapter() {


                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                for (int i = 0; i < 1; i++) {
                                    ctx.writeAndFlush(getByteBuffer("time"));
                                }
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                String id = ctx.channel().id().asShortText();
                                String content = getStr((ByteBuf) msg);
                                out(id + ":" + content);

//                                ctx.writeAndFlush(getByteBuffer("end"));
//                                ctx.close();
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                cause.printStackTrace();
                                super.exceptionCaught(ctx, cause);
                            }
                        });
                    }
                });

        List<ChannelFuture> futureList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 800));
            futureList.add(future);
        }

        for (ChannelFuture future : futureList) {
            future.channel().closeFuture().sync();
        }

        group.shutdownGracefully();
    }


    private static ByteBuf getByteBuffer(String s) {
        s = s + "\n";
        return Unpooled.copiedBuffer(s.getBytes());
    }


    private static void out(String s) {
        System.out.println(Thread.currentThread().getName() + "::" + s);
    }

    private static String getStr(ByteBuf buffer) {
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);
        return new String(bytes);
    }
}
