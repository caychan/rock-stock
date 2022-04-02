package com.cay.rockstock.rocketmq.consumer;

import com.cay.rockstock.rocketmq.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
public class ConsumerConfig {

    private MQPushConsumer createConsumer(String topic, MessageListenerConcurrently listenerConcurrently) throws MQClientException {
        log.info("init consumer: {}", topic);
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(topic + "-consumer-group");
        consumer.setInstanceName(topic + "-Instance");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setConsumeThreadMin(5);
        consumer.setConsumeThreadMax(10);
        consumer.setNamesrvAddr(Config.NAME_SERVER);
        consumer.subscribe(topic, "*");
        consumer.registerMessageListener(listenerConcurrently);
        consumer.start();
        return consumer;
    }

    @Bean(name = "helloConsumer")
    public MQPushConsumer helloConsumer(MessageListenerConcurrently helloConsumerListener) throws MQClientException {
        return createConsumer("hello-topic", helloConsumerListener);
    }

    @Bean(name = "helloTxConsumer")
    public MQPushConsumer helloTxConsumer(MessageListenerConcurrently helloTxConsumerListener) throws MQClientException {
        return createConsumer("hello-tx-topic", helloTxConsumerListener);
    }


}
