package com.itiswho.ccb;

import com.itiswho.ccb.entity.Room;
import com.itiswho.ccb.entity.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ccb
 */
public class NettyFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();
    private static Map<String, Room> roomMap = new ConcurrentHashMap<>();
    private User user;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame) throws Exception {

        if (webSocketFrame instanceof TextWebSocketFrame) {
            String request = ((TextWebSocketFrame) webSocketFrame).text();
            System.out.println(request);
            channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("hello".toUpperCase(Locale.US)));
        } else {
            String message = "unsupported frame";
            throw new UnsupportedOperationException(message);
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }
}
