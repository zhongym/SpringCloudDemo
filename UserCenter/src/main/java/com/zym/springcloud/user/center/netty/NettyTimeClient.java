package com.zym.springcloud.user.center.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NettyTimeClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture future = bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelHandlerAdapter() {


                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                ctx.writeAndFlush(getByteBuffer("time"));
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                String content = getStr((ByteBuf) msg);
                                out(content);

                                ctx.writeAndFlush(getByteBuffer("end"));
                            }
                        });
                    }
                })
                .connect(new InetSocketAddress("127.0.0.1", 800))
                .sync();

        future.channel().closeFuture().sync();
        group.shutdownGracefully();
    }


    private static ByteBuf getByteBuffer(String s) {
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
