package com.cay.rockstock.rocketmq.consumer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class HelloTransactionListener implements TransactionListener {
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object result) {
        log.info("hello transaction listener:{}", msg);
        HelloTransactionResult tmp = (HelloTransactionResult) result;
        tmp.time = System.currentTimeMillis();
        return LocalTransactionState.COMMIT_MESSAGE;
//        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        log.info("hello transaction check:{}", msg);
        return LocalTransactionState.COMMIT_MESSAGE;
    }


    @Data
    public static class HelloTransactionResult {
        private long time;
    }
}
