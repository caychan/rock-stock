package com.cay.rockstock.controller;


import com.cay.rockstock.beans.CommonResponse;
import com.cay.rockstock.beans.Response;
import com.cay.rockstock.config.redis.RedisKeys;
import com.cay.rockstock.service.RedisService;
import com.cay.rockstock.spider.SpiderProcessor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
public class StockController {

    @Resource
    private SpiderProcessor spiderProcessor;
    @Resource(name = "commonExecutor")
    private ExecutorService commonExecutor;
    @Resource
    private RedisService redisService;


    private static final AtomicBoolean running = new AtomicBoolean(false);

    @GetMapping(value = "/spider")
    public CommonResponse startSpider() {
        log.info("process spider task, running:{}", running.get());
        if (running.get()) {
            return CommonResponse.fail();
        }
        running.set(true);
//        redisService.trySimpleLock(RedisKeys.STOCK_STARTER);
        commonExecutor.submit(() -> spiderProcessor.startSpider());
        return CommonResponse.success();
    }

}
