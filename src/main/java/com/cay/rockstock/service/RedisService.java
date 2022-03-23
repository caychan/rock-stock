package com.cay.rockstock.service;


import com.cay.rockstock.beans.CommonResponse;
import com.cay.rockstock.config.redis.RedisKeys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Slf4j
@Service
public class RedisService {

    @Resource(name = "jedis")
    private Jedis jedis;

    public boolean tryLock(String key, long expireSeconds) {
        String result = jedis.set(key, "1", new SetParams().ex(expireSeconds).nx());
        if (!"OK".equalsIgnoreCase(result)) {
            log.warn("redis lock exists: {},{}", key, result);
            return false;
        }
        return true;
    }

    public boolean tryLock(String key, String value, long expireSeconds) {
        String result = jedis.set(key, value, new SetParams().ex(expireSeconds).nx());
        if (!"OK".equalsIgnoreCase(result)) {
            log.warn("redis lock exists: {}, {}", key, value);
            return false;
        }
        return true;
    }

    public void deleteLock(String key) {
        jedis.del(key);
    }

    /**
     * 删除redis锁
     *
     * @param key   锁的key
     * @param value 锁的value，如果redis里的key对应的value不是该value，则不会删除key
     */
    public void deleteLock(String key, String value) {
        jedis.eval(RedisKeys.DEL_LOCK_SCRIPT, 1, key, value);
    }

    public String getKey(String key) {
        return jedis.get(key);
    }

}
