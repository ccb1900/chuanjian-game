package com.itiswho.chuanjian;

import com.itiswho.chuanjian.netty.NettyServer;

public class GameServer {
    /**
     * @param args
     * @throws Exception
     * @url https://netty.io/wiki/index.html
     */
    public static void main(String[] args) throws Exception {
        NettyServer.start(7002);
    }
}
