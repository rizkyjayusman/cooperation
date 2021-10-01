package com.alami.cooperation.service;

import com.alami.cooperation.controller.filter.Pagination;
import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {

    Page<Transaction> getTransactionList(Pageable pageable);

    void createTransaction(TransactionDto transactionDto);

}
