package com.zym.springcloud.user.center.netty.http;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

public class HandlerProcessor {

    public static HandlerProcessor processor = new HandlerProcessor();

    private HandlerMapping mapping = new HandlerMapping();
    private HandlerAdapter adapter = new HandlerAdapter();

    public void processor(ChannelHandlerContext ctx, FullHttpRequest request) {
        Handler handler = mapping.getHandler(request);
        if (handler == null) {
            noHandlerFound(ctx, request);
            return;
        }
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        try {
            adapter.handle(request, response, handler);
        } catch (Exception e) {
            response.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void noHandlerFound(ChannelHandlerContext ctx, FullHttpRequest request) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
