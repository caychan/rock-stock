package com.cay.rockstock.config.redis;

public interface RedisKeys {

    String DEL_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] " +
            "    then return redis.call('del', KEYS[1]) " +
            "    else return -1" +
            "    end";

    String STOCK_STARTER = "str:stock:starter";
}
