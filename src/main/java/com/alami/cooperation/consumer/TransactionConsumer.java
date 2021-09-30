package com.alami.cooperation.consumer;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.entity.TransactionHistory;
import com.alami.cooperation.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConsumer {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    public void consume(TransactionDto transactionDto) {
        transactionHistoryService.createTransactionHistory(transactionDto);
    }
}
