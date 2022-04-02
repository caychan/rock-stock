package com.cay.rockstock.rocketmq.producer;

import com.cay.rockstock.rocketmq.Config;
import com.cay.rockstock.rocketmq.consumer.HelloTransactionListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;


@Slf4j
@Configuration
public class ProducerConfig {

    @Resource
    private HelloTransactionListener helloTransactionListener;
    @Resource
    private ThreadPoolExecutor commonExecutor;


    private DefaultMQProducer createProducer(String topic) throws MQClientException {
        log.info("init producer: {}", topic);
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(topic + "-group");
        defaultMQProducer.setCreateTopicKey(topic);
        defaultMQProducer.setNamesrvAddr(Config.NAME_SERVER);
        defaultMQProducer.setSendMsgTimeout(3000);
        defaultMQProducer.setRetryAnotherBrokerWhenNotStoreOK(true);
        defaultMQProducer.start();
        return defaultMQProducer;
    }

    private TransactionMQProducer createTransactionProducer(String topic, TransactionListener transactionListener, ExecutorService executorService) throws MQClientException {
        log.info("init transaction producer: {}", topic);
        TransactionMQProducer producer = new TransactionMQProducer(topic + "-tx-group");
        producer.setNamesrvAddr(Config.NAME_SERVER);
        producer.setCreateTopicKey(topic);
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.setRetryAnotherBrokerWhenNotStoreOK(true);
        producer.start();
        return producer;
    }


    @Bean(value = "helloProducer")
    public DefaultMQProducer createHelloProducer() throws MQClientException {
        return createProducer("hello-topic");
    }

    @Bean(value = "helloTxProducer")
    public TransactionMQProducer createHelloTransactionProducer() throws MQClientException {
        return createTransactionProducer("hello-tx-topic", helloTransactionListener, commonExecutor);
    }

}
