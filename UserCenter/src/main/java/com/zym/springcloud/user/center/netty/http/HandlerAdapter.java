package com.zym.springcloud.user.center.netty.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public class HandlerAdapter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public void handle(FullHttpRequest request, FullHttpResponse response, Handler handler) {

        Object value = handler.run(request);
        try {
            String s = objectMapper.writeValueAsString(value);
            response.headers().add("Content-Type", "application/json");
            response.content().writeBytes(s.getBytes());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
