package com.cay.rockstock.mq;

import com.cay.rockstock.rocketmq.consumer.HelloTransactionListener;
import com.cay.rockstock.rocketmq.producer.MQProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloProducerTest {


    @Resource
    private MQProducer mqProducer;

    @Test
    public void testProducer() {
        SendResult result = mqProducer.pubHello("hello-v-" + System.currentTimeMillis());
        log.info("produce result : {}", result);
    }


    @Test
    public void testTxProducer() {
        HelloTransactionListener.HelloTransactionResult res = new HelloTransactionListener.HelloTransactionResult();
        SendResult result = mqProducer.pubHelloTx("hello-tx-value-" + System.currentTimeMillis(), res);
        log.info("produce tx msg, result:{}, res:{}", result, res);
    }
}
