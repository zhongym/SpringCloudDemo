package com.zym.springcloud.user.center.netty.http;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerMapping {

    public HandlerMapping() {
        this.map = new HashMap<>();
        map.put("/api/get", new Handler() {
            @Override
            public Object run(FullHttpRequest request) {
                QueryStringDecoder qs = new QueryStringDecoder(request.uri());
                List<String> name = qs.parameters().get("name");

                return name;
            }
        });
    }

    private Map<String, Handler> map;


    Handler getHandler(FullHttpRequest request) {
        QueryStringDecoder qs = new QueryStringDecoder(request.uri());
        return map.get(qs.path());
    }

}
