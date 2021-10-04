package com.alami.cooperation.service;

import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionHistoryService {

    TransactionHistory createTransactionHistory(TransactionDto transactionDto);

    Page<TransactionHistory> getTransactionHistoryList(TransactionFilter transactionFilter, Pageable pageable);

}
