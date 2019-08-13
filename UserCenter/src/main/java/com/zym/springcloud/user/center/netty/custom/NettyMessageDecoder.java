package com.zym.springcloud.user.center.netty.custom;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        NettyMessage msg = new NettyMessage();
        Header h = msg.getHeader();
        h.setCrcCode(frame.readInt());
        h.setLength(frame.readInt());
        h.setSessionId(frame.readLong());
        h.setType(frame.readByte());
        h.setPriority(frame.readByte());
        int attachmentSize = frame.readInt();
        if (attachmentSize > 0) {
            for (int i = 0; i < attachmentSize; i++) {
                byte[] keyBytes = new byte[frame.readInt()];
                frame.readBytes(keyBytes);
                String key = new String(keyBytes);

                byte[] valueBytes = new byte[frame.readInt()];
                frame.readBytes(valueBytes);
                String value = new String(valueBytes);
                h.getAttachment().put(key, value);
            }
        }

        int objectSize = frame.readInt();
        if (objectSize > 0) {
            byte[] valueBytes = new byte[objectSize];
            frame.readBytes(valueBytes);
            String value = new String(valueBytes);
            msg.setBody(value);
        }
        return msg;
    }
}
