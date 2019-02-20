package com.itiswho.ccb.server;

import com.itiswho.ccb.entity.Room;
import com.itiswho.ccb.entity.User;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BinaryWebSocketFrame  包含了二进制数据
 * TextWebSocketFrame 包含了文本数据
 * ContinuationWebSocketFrame 包含了属于上一个BinaryWebSocketFrame或TextWebSocketFrame的文本数据或二进制数据
 * CloseWebSocketFrame 表示一个CLOSE请求，包含一个关闭状态码和原因
 * PingWebSocketFrame 请求传输一个PongWebSocketFrame
 * PongWebSocketFrame 作为一个对PingWebSocketFrame的响应被发送
 * static 变量是线程共享的
 * @author ccb
 */
public class NettyFrameHandler extends SimpleChannelInboundHandler<Object> {
    /**
     * 线程共享的数据
     * 房间列表
     */
    private static Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();
    private static Map<String, Room> roomMap = new ConcurrentHashMap<>();

    // 失败计数器：未收到client端发送的ping请求
    private int unRecPingTimes = 0;
    // 定义服务端没有收到心跳消息的最大次数
    private static final int MAX_UN_REC_PING_TIMES = 3;
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled
            .unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",
                    CharsetUtil.UTF_8));
    private User user;
    private void sendResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response){
        if (response.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
//            response.setContentLength(response, response.content().readableBytes());

            ChannelFuture f = ctx.channel().writeAndFlush(response);
            if ( response.status().code() != 200) {
                f.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object webSocketFrame) throws Exception {
//        if (webSocketFrame instanceof FullHttpRequest) {
//            sendResponse(channelHandlerContext,(FullHttpRequest) webSocketFrame,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
//        }else
        System.out.println(webSocketFrame.getClass());
        if (webSocketFrame instanceof TextWebSocketFrame) {
            System.out.println(unRecPingTimes + "=unRecPingTimes"+channelHandlerContext.channel().id());
            String request = ((TextWebSocketFrame) webSocketFrame).text();
            System.out.println(request);
            unRecPingTimes = 0;
            channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("退下吧"));
        } else if (webSocketFrame instanceof PingWebSocketFrame) {
            channelHandlerContext.channel().writeAndFlush(new PongWebSocketFrame());
        } else if (webSocketFrame instanceof  CloseWebSocketFrame) {
            System.out.println(webSocketFrame);
            channelHandlerContext.channel().writeAndFlush(new PongWebSocketFrame());
        }
        else {
            String message = "unsupported frame";
            throw new UnsupportedOperationException(message);
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().id()+"客户端加入");
//        if (!channelGroupMap.containsKey(user.toString())) {
//            channelGroupMap.put(user.toString(), new DefaultChannelGroup(GlobalEventExecutor.INSTANCE));
//        }
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id()+"客户端离开");
        if (user !=null && channelGroupMap.containsKey(user.toString())) {
            channelGroupMap.get(user.toString()).remove(ctx.channel());
        }
        super.handlerRemoved(ctx);
    }

    private void timeout(ChannelHandlerContext ctx) {

    }
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println(evt.getClass());
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String type = "";

            if (event.state() == IdleState.READER_IDLE) {
                System.out.println(unRecPingTimes + "=unRecPingTimes"+ctx.channel().id());
                if (unRecPingTimes >= MAX_UN_REC_PING_TIMES) {
                    System.out.println("服务器读超时超过3次，关闭channel"+ctx.channel().id());
                    timeout(ctx);
                    ctx.close();
                }
                type = "read idle";
                unRecPingTimes++;
            } else if (event.state() == IdleState.WRITER_IDLE) {
                type = "write idle";
            } else if (event.state() == IdleState.ALL_IDLE) {
                type = "all idle";
            }

            ctx.writeAndFlush(new BinaryWebSocketFrame(HEARTBEAT_SEQUENCE.duplicate())).addListener(
                    ChannelFutureListener.CLOSE_ON_FAILURE);

            System.out.println( ctx.channel().remoteAddress()+"超时类型：" + type + (new Date()).getTime());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端连接开启..."+ctx.channel().id());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端连接关闭..."+ctx.channel().id());
        super.channelInactive(ctx);
    }
}
