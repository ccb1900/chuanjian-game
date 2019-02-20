package com.itiswho.ccb.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author admin
 */
public class NettyServer {
    /**
     * @param port
     * @throws Exception
     * @url @url https://netty.io/4.1/xref/io/netty/example/http/websocketx/server/package-summary.html
     */
    public static void start(int port) throws Exception {
        EventLoopGroup masterGroup = new NioEventLoopGroup(1);
        EventLoopGroup slaveGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();

            b.group(masterGroup, slaveGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new NettyServerInit(null));
            Channel channel = b.bind(port).sync().channel();

            System.out.println("open your browser 127.0.0.1:" + port);
            channel.closeFuture().sync();
        } finally {
            masterGroup.shutdownGracefully();
            slaveGroup.shutdownGracefully();
        }
    }
}
