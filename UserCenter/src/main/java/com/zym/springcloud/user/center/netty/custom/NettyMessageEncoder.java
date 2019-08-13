package com.zym.springcloud.user.center.netty.custom;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf buf) throws Exception {
        Header header = msg.getHeader();
        buf.writeInt(header.getCrcCode());
        buf.writeInt(header.getLength());
        buf.writeLong(header.getSessionId());
        buf.writeByte(header.getType());
        buf.writeByte(header.getPriority());
        buf.writeInt(header.getAttachment().size());
        header.getAttachment().forEach((key, value) -> {
            byte[] keyBytes = key.getBytes();
            byte[] valueBytes = value.getBytes();
            buf.writeInt(keyBytes.length);
            buf.writeBytes(keyBytes);

            buf.writeInt(valueBytes.length);
            buf.writeBytes(valueBytes);
        });

        if (msg.getBody() != null) {
            byte[] valueBytes = msg.getBody().getBytes();
            buf.writeInt(valueBytes.length);
            buf.writeBytes(valueBytes);
        } else {
            buf.writeInt(0);
        }
        int writerIndex = buf.readableBytes() - 8;
        buf.setInt(4, writerIndex);
    }
}
