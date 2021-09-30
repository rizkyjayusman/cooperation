package com.alami.cooperation.service;

import com.alami.cooperation.controller.filter.Pagination;
import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactionList(TransactionFilter transactionFilter, Pagination pagination);

    void createTransaction(TransactionDto transactionDto);

}
