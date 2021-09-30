package com.alami.cooperation.publisher;

import com.alami.cooperation.consumer.TransactionConsumer;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionPublisher {

    @Autowired
    private TransactionConsumer transactionConsumer;

    public void publish(TransactionDto transactionDto) {
        transactionConsumer.consume(transactionDto);
    }
}
