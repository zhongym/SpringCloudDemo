package com.zym.springcloud.user.center.netty.custom;

public class NettyMessage {
    private Header header = new Header();
    private String body;

    public static NettyMessage build(MessageType type) {
        NettyMessage message = new NettyMessage();
        message.getHeader().setType(type.value());
        return message;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage{" +
                "header=" + header +
                ", object='" + body + '\'' +
                '}';
    }
}
