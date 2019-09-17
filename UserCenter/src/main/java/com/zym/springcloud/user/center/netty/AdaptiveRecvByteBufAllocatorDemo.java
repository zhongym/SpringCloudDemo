package com.zym.springcloud.user.center.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.RecvByteBufAllocator;

public class AdaptiveRecvByteBufAllocatorDemo {

    public static void main(String[] args) {
        AdaptiveRecvByteBufAllocator recvByteBufAllocator = AdaptiveRecvByteBufAllocator.DEFAULT;
        RecvByteBufAllocator.Handle handle = recvByteBufAllocator.newHandle();
        ByteBufAllocator alloc = UnpooledByteBufAllocator.DEFAULT;
        ByteBuf buf = handle.allocate(alloc);

        System.out.println(buf.writableBytes());

        handle.record(1025);
        buf = handle.allocate(alloc);
        System.out.println(buf.writableBytes());

        handle.record(50);
        buf = handle.allocate(alloc);
        System.out.println(buf.writableBytes());

        handle.record(50);
        buf = handle.allocate(alloc);
        System.out.println(buf.writableBytes());
    }
}
