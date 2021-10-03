package com.alami.cooperation.consumer;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConsumer {

    @Autowired
    private TransactionService transactionService;

    public void consume(TransactionDto transactionDto) {
        transactionService.createTransaction(transactionDto);
    }
}
