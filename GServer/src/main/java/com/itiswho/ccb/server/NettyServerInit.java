package com.itiswho.ccb.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author admin
 */
public class NettyServerInit extends ChannelInitializer<SocketChannel> {

    private static final String WEBSOCKET_PATH = "/websocket";
    /**
     * 读超时
     */
    private static final int READ_IDLE_TIME_OUT = 40;
    /**
     * 写超时
     */
    private static final int WRITE_IDLE_TIME_OUT = 50;
    /**
     * 所有超时
     */
    private static final int ALL_IDLE_TIME_OUT = 70;
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
        channelPipeline.addLast(new IdleStateHandler(READ_IDLE_TIME_OUT, WRITE_IDLE_TIME_OUT, ALL_IDLE_TIME_OUT, TimeUnit.SECONDS));
        channelPipeline.addLast(new WebSocketServerCompressionHandler());
        channelPipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
        channelPipeline.addLast(new NettyIndexPageHandler(WEBSOCKET_PATH));
        channelPipeline.addLast(new NettyFrameHandler());
    }
}
