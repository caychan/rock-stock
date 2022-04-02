package com.cay.rockstock.rocketmq.producer;

import com.cay.rockstock.rocketmq.consumer.HelloTransactionListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component(value = "mqProducer")
public class MQProducer {

    @Autowired
    private DefaultMQProducer helloProducer;
    @Autowired
    private TransactionMQProducer helloTxProducer;


    public SendResult pubHello(String value) {
        return pub(helloProducer, value);
    }

    public SendResult pubHelloTx(String value, Object res) {
        return pubTxMessage(helloTxProducer, value, res);
    }

    public SendResult pub(DefaultMQProducer producer, String key, String value)  {
        Message message = new Message(producer.getCreateTopicKey(), key, value.getBytes(StandardCharsets.UTF_8));
        try {
            return producer.send(message);
        } catch (Exception e) {
            log.error("pub msg error:{},{},{}", producer.getCreateTopicKey(),key, value);
            return null;
        }
    }


    public SendResult pub(DefaultMQProducer producer, String value) {
        Message message = new Message(producer.getCreateTopicKey(), value.getBytes(StandardCharsets.UTF_8));
        try {
            return producer.send(message);
        } catch (Exception e) {
            log.error("pub msg error:{},{}", producer.getCreateTopicKey(), value);
            return null;
        }
    }


    public SendResult pubTxMessage(TransactionMQProducer producer, String value, Object res) {
        Message message = new Message(producer.getCreateTopicKey(), value.getBytes(StandardCharsets.UTF_8));
        try {
            return producer.sendMessageInTransaction(message, res);
        } catch (Exception e) {
            log.error("pub msg error:{},{}", producer.getCreateTopicKey(), value);
            return null;
        }
    }

}
