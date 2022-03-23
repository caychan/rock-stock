package com.cay.rockstock.redis;

import com.alibaba.fastjson.JSON;
import com.cay.rockstock.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
public class JedisTests {

    @Autowired
    private Jedis jedis;
    @Autowired
    private RedisService redisService;


    @Test
    public void testSimpleLock() {
        boolean suc = redisService.tryLock("lock", 3);
        log.info("try lock:{}", suc);
        redisService.deleteLock("lock1");
        log.info("Lock : {}", redisService.getKey("lock"));
    }

    @Test
    public void testLock() {
        boolean suc = redisService.tryLock("lock", "value", 3);
        log.info("try lock:{}", suc);
        redisService.deleteLock("lock1", "value");
        redisService.deleteLock("lock", "value1");
        log.info("Lock : {}", redisService.getKey("lock"));
        redisService.deleteLock("lock", "value");
        log.info("Lock : {}", redisService.getKey("lock"));
    }

    @Test
    public void testGet() {
        System.out.println(jedis.get("strKey"));
    }

    @Test
    public void testString() {
        jedis.set("strKey", "strValue",SetParams.setParams().ex(3L));
        System.out.println(jedis.get("strKey"));
    }

    @Test
    public void testSerializable() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUserName("朝雾轻寒");
        user.setUserSex("男");
        jedis.set("user", JSON.toJSONString(user), SetParams.setParams().ex(3L));
        UserEntity user2 = JSON.parseObject(jedis.get("user"), UserEntity.class);
        System.out.println("user:" + user2.getId() + "," + user2.getUserName() + "," + user2.getUserSex());
    }

}
