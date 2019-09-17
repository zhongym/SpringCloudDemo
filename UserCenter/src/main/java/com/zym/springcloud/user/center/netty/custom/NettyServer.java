package com.zym.springcloud.user.center.netty.custom;

import com.zym.springcloud.user.center.jdk.io.TimeServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;

import java.net.InetSocketAddress;
import java.util.List;

public class NettyServer implements TimeServer {
    private Channel channel;

    @Override
    public void start(int port) {
        NioEventLoopGroup pGroup = new NioEventLoopGroup(3);
        NioEventLoopGroup cGroup = new NioEventLoopGroup(4);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(pGroup, cGroup)
                .channel(NioServerSocketChannel.class)
                //配置NioServerSocketChannel参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                //配置NioSocketChannel参数
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                //NioServerSocketChannel
                .handler(new LoggingHandler(LogLevel.DEBUG))
                //NioSocketChannel
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //解决tcp粘包和折包、解码
                        ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                        //流量整形 每1000毫秒钟限制只能读取1M,写1M流量，超过的放到队列延迟发送
                        ch.pipeline().addLast(new ChannelTrafficShapingHandler(1024 * 1024,1024 * 1024,1000));
                        //编码
                        ch.pipeline().addLast(new NettyMessageEncoder());
                        //超时
                        ch.pipeline().addLast(new ReadTimeoutHandler(30));
                        //登录验证
                        ch.pipeline().addLast(new MessageToMessageDecoder<NettyMessage>() {

                            @Override
                            protected void decode(ChannelHandlerContext ctx, NettyMessage message, List<Object> out) throws Exception {
                                if (message.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
                                    System.out.println("处理登录消息" + message);
                                    String ip = ctx.channel().remoteAddress().toString();
                                    //非白名单
                                    if (!ip.contains("127.0.0.1")) {
                                        ctx.writeAndFlush(resp(-1));
                                        return;
                                    }
                                    //重复登录
                                    if (false) {
                                        ctx.writeAndFlush(resp(0));
                                        return;
                                    }
                                    //正常登录
                                    ctx.writeAndFlush(resp(1));
                                }
                                //往下传
                                else {
                                    out.add(message);
                                }
                            }

                            private NettyMessage resp(int code) {
                                NettyMessage message = new NettyMessage();
                                message.getHeader().setType(MessageType.LOGIN_RESP.value());
                                message.setBody(code + "");
                                return message;
                            }

                            @Override
                            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                ctx.flush();
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                cause.printStackTrace();
                                ctx.close();
                            }
                        });
                        //心跳
                        ch.pipeline().addLast(new MessageToMessageDecoder<NettyMessage>() {

                            @Override
                            protected void decode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws
                                    Exception {
                                if (msg.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
                                    System.out.println("收到客户端的心跳信息");
                                    ctx.writeAndFlush(NettyMessage.build(MessageType.HEARTBEAT_RESP));
                                }
                                //往下传
                                else {
                                    out.add(msg);
                                }
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                super.exceptionCaught(ctx, cause);
                            }
                        });
                        //发送业务消息
                        ch.pipeline().addLast(new MessageToMessageDecoder<NettyMessage>() {
                            @Override
                            protected void decode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws
                                    Exception {
                                System.out.println("接收到业务消息" + msg);

//                                        NettyMessage build = NettyMessage.build(MessageType.SERVICE_REQ);
//                                        build.getHeader().getAttachment().put("server-key", "value");
//                                        build.setBody("服务消息");
//                                        ctx.writeAndFlush(build);
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                super.exceptionCaught(ctx, cause);
                            }

                            @Override
                            public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
                                System.out.println("客户端连接已断开：" + ctx.channel().id().asLongText());
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


    public static void main(String[] args) {
        new NettyServer().start(900);
    }
}
