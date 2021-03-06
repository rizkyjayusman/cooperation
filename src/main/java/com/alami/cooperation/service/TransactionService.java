package com.alami.cooperation.service;

import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    Page<Transaction> getTransactionList(Pageable pageable);

    Transaction createTransaction(TransactionDto transactionDto);

    Page<Transaction> getTransactionList(TransactionFilter transactionFilter, Pageable pageable);
}
