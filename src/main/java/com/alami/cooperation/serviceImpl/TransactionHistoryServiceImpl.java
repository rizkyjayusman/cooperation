package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.TransactionHistory;
import com.alami.cooperation.repository.TransactionHistoryRepository;
import com.alami.cooperation.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public TransactionHistory createTransactionHistory(TransactionDto transactionDto) {
        TransactionHistory transaction = new TransactionHistory();
        transaction.setMemberId(transactionDto.getMemberId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setTransactionDate(transactionDto.getTransactionDate());
        transaction.setCreatedDate(new Date());
        return transactionHistoryRepository.save(transaction);
    }

    @Override
    public Page<TransactionHistory> getTransactionHistoryList(TransactionFilter transactionFilter, Pageable pageable) {
        if(transactionFilter.getMemberId() != null) {
            return transactionHistoryRepository.findByMemberId(transactionFilter.getMemberId(), pageable);
        }

        if(transactionFilter.getFromDate() == null) {
            transactionFilter.setFromDate(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(3)));
        }

        if(transactionFilter.getEndDate() == null) {
            transactionFilter.setFromDate(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)));
        }

        return transactionHistoryRepository.findByTransactionDateBetween(transactionFilter.getFromDate(), transactionFilter.getEndDate(), pageable);
    }

}
