package com.itiswho.ccb;

import com.google.gson.Gson;
import com.itiswho.ccb.entity.Room;
import com.itiswho.ccb.entity.SingleMessage;
import com.itiswho.ccb.entity.MsgType;
import com.itiswho.ccb.entity.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

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
            Gson gson = new Gson();
            MsgType msgType = gson.newBuilder().serializeNulls().create().fromJson(request, MsgType.class);

            switch (msgType.getType()) {
                case "msg":
                    SingleMessage message = msgType.getContent();
                    user = new User("cc" + message.getFromUserId(), message.getFromUserId());
                    if (!channelGroupMap.containsKey(message.getRoomId())) {
                        Room room = new Room("1", "1");
                        roomMap.put("1", room);
                        channelGroupMap.put(room.getId(), new DefaultChannelGroup(GlobalEventExecutor.INSTANCE));
                    }
                    user.setRoom(new Room("1", "1"));
                    channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(message.getContent().toUpperCase(Locale.US)));
                    break;
                default:
                    String messages = "unsupported format";
                    throw new UnsupportedOperationException(messages);
            }
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
