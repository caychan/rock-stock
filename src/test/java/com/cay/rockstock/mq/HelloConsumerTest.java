package com.cay.rockstock.mq;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloConsumerTest {

    @Test
    public void testConsumer() {
        log.info("end");
    }
}
