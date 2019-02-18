package com.itiswho.ccb;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.ssl.SslContext;

/**
 * @author admin
 */
public class NettyServerInit extends ChannelInitializer<SocketChannel> {

    private static final String WEBSOCKET_PATH = "/websocket";

    private final SslContext sslCtx;

    NettyServerInit(SslContext sslContext) {
        this.sslCtx = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();

        if (sslCtx != null) {
            channelPipeline.addLast(sslCtx.newHandler(socketChannel.alloc()));
        }

        channelPipeline.addLast(new HttpServerCodec());
        channelPipeline.addLast(new HttpObjectAggregator(65536));
        channelPipeline.addLast(new WebSocketServerCompressionHandler());
        channelPipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
        channelPipeline.addLast(new NettyIndexPageHandler(WEBSOCKET_PATH));
        channelPipeline.addLast(new NettyFrameHandler());
    }
}
