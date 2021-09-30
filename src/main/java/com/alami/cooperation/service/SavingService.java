package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;

import java.math.BigDecimal;

public interface SavingService {

    void createSavingTransaction(TransactionDto transactionDto);

    void createDebitTransaction(TransactionDto transactionDto);

    BigDecimal getTotalSaving();
}
