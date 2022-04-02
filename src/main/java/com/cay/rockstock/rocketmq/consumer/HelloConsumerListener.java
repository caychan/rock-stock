package com.cay.rockstock.rocketmq.consumer;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component(value = "helloConsumerListener")
public class HelloConsumerListener implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        log.info("hello consume, context:{}", JSON.toJSONString(context));
        for (MessageExt messageExt : msgs) {
            String body = new String(messageExt.getBody());
            log.info("hello consumer, msg body:{}", body);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
