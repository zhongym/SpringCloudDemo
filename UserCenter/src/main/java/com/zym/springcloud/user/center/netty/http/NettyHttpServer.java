package com.zym.springcloud.user.center.netty.http;

import com.zym.springcloud.user.center.jdk.io.TimeServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

public class NettyHttpServer implements TimeServer {
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
                        //创建成HttpRequest、HttpContent
                        ch.pipeline().addLast("HttpRequestDecoder", new HttpRequestDecoder());
                        //将HttpRequest、HttpContent合并成FullHttpRequest
                        ch.pipeline().addLast("HttpObjectAggregator", new HttpObjectAggregator(65536));

                        ch.pipeline().addLast("HttpResponseEncoder", new HttpResponseEncoder());

                        ch.pipeline().addLast("HttpRequestHandler", new SimpleChannelInboundHandler<FullHttpRequest>() {
                            @Override
                            protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
                                HandlerProcessor.processor.processor(ctx, request);
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

    public static void main(String[] args) {
        new NettyHttpServer().start(80);
    }
}
