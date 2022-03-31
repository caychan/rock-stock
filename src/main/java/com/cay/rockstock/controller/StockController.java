package com.cay.rockstock.controller;


import com.cay.rockstock.beans.CommonResponse;
import com.cay.rockstock.config.Constants;
import com.cay.rockstock.config.StockConfig;
import com.cay.rockstock.config.redis.RedisKeys;
import com.cay.rockstock.service.RedisService;
import com.cay.rockstock.spider.SpiderProcessor;
import com.cay.rockstock.spider.StcnSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@RestController
public class StockController {

    @Resource
    private SpiderProcessor spiderProcessor;
    @Resource
    private StcnSpider stcnSpider;
    @Resource(name = "commonExecutor")
    private ExecutorService commonExecutor;
    @Resource
    private RedisService redisService;


    private static final AtomicBoolean running = new AtomicBoolean(false);

    @GetMapping(value = "/spider")
    public CommonResponse startSpider() {
        boolean lock = redisService.tryLock(RedisKeys.STOCK_STARTER, Constants.ONE_HOUR_SECONDS);
        log.info("process spider task, running:{}", lock);
        if (!lock) {
            return CommonResponse.fail();
        }
        commonExecutor.submit(() -> {
            stcnSpider.startSpider(StockConfig.stockIds);
            redisService.deleteLock(RedisKeys.STOCK_STARTER);
        });
        return CommonResponse.success();
    }

    @GetMapping(value = "/local_spider")
    public CommonResponse startLocalSpider() {
        log.info("process local spider task, running:{}", running.get());
        if (running.get()) {
            return CommonResponse.fail();
        }
        running.set(true);
        commonExecutor.submit(() -> spiderProcessor.startSpider());
        return CommonResponse.success();
    }

}
