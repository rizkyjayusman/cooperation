package com.alami.cooperation.service;

import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.TransactionHistory;

import java.util.List;

public interface TransactionHistoryService {

    List<TransactionHistory> getTransactionHistoryList(TransactionFilter transactionFilter);

    void createTransactionHistory(TransactionDto transactionDto);

}
