package com.itiswho.ccb.server;

import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");

    }
}
