package com.itiswho.ccb;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author admin
 */
public final class NettyServerIndexPage {
    private static final String NEWLINE = "\r\n";

    public static ByteBuf getContent(String webSocketLocation) {
        return Unpooled.copiedBuffer("hahaha" + NEWLINE + webSocketLocation, CharsetUtil.US_ASCII);
    }

    private NettyServerIndexPage() {
    }
}
