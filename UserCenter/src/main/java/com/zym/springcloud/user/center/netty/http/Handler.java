package com.zym.springcloud.user.center.netty.http;

import io.netty.handler.codec.http.FullHttpRequest;

public interface Handler {
    Object run(FullHttpRequest request);
}
