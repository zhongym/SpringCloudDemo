package com.zym.springcloud.user.center.netty.custom;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture future = bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                        ch.pipeline().addLast(new NettyMessageEncoder());
                        //超时
                        ch.pipeline().addLast(new ReadTimeoutHandler(30));
                        //握手、安全
                        ch.pipeline().addLast(new MessageToMessageDecoder<NettyMessage>() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                //发送握手消息
                                NettyMessage msg = new NettyMessage();
                                msg.getHeader().setType(MessageType.LOGIN_REQ.value());
                                ctx.writeAndFlush(msg);
                                System.out.println("发送登录消息");
                            }

                            @Override
                            protected void decode(ChannelHandlerContext ctx, NettyMessage message, List<Object> out) throws Exception {
                                if (message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
                                    if (!"1".equals(message.getBody())) {
                                        System.out.println("认证失败，关闭连接===========>");
                                        ctx.close();
                                        return;
                                    }
                                    System.out.println("认证成功");
                                }
                                //
                                out.add(message);
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                cause.printStackTrace();
                                super.exceptionCaught(ctx, cause);
                            }
                        });
                        //心跳
                        ch.pipeline().addLast(new MessageToMessageDecoder<NettyMessage>() {

                            @Override
                            protected void decode(ChannelHandlerContext ctx, NettyMessage message, List<Object> out) throws Exception {
                                if (message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
                                    //定时发送
                                    ctx.executor().scheduleAtFixedRate(() -> {
                                        System.out.println("发送心跳信息===》");
                                        ctx.writeAndFlush(NettyMessage.build(MessageType.HEARTBEAT_REQ));
                                    }, 0, 5, TimeUnit.SECONDS);
//                                    return;
                                }
                                if (message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
                                    System.out.println("接收心跳信息回复===》");
                                    return;
                                }
                                out.add(message);
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                super.exceptionCaught(ctx, cause);
                            }
                        });
                        //发送业务消息
                        ch.pipeline().addLast(new MessageToMessageDecoder<NettyMessage>() {

                            @Override
                            protected void decode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
                                System.out.println("接收到业务消息" + msg);

                                NettyMessage build = NettyMessage.build(MessageType.SERVICE_REQ);
                                build.getHeader().getAttachment().put("key", "value");
                                build.setBody("客户端消息");
                                ctx.writeAndFlush(build);
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                super.exceptionCaught(ctx, cause);
                            }
                        });

                    }
                })
                .connect(new InetSocketAddress("127.0.0.1", 900))
                .sync();
        try {

            future.channel().closeFuture().sync();
            group.shutdownGracefully();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
