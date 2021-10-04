package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.mapper.TransactionMapper;
import com.alami.cooperation.publisher.TransactionPublisher;
import com.alami.cooperation.repository.TransactionRepository;
import com.alami.cooperation.service.TransactionHistoryService;
import com.alami.cooperation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @Autowired
    private TransactionPublisher transactionPublisher;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Page<Transaction> getTransactionList(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
        Transaction transaction = TransactionMapper.createTransaction(transactionDto);
        Transaction savedTransaction =  transactionRepository.save(transaction);
        transactionPublisher.publish(transactionDto);
        return savedTransaction;
    }

    @Override
    public Page<Transaction> getTransactionList(TransactionFilter transactionFilter, Pageable pageable) {
        if(transactionFilter.getMemberId() != null) {
            return transactionRepository.findByMemberId(transactionFilter.getMemberId(), pageable);
        }

        if(transactionFilter.getFromDate() == null) {
            transactionFilter.setFromDate(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(3)));
        }

        if(transactionFilter.getEndDate() == null) {
            transactionFilter.setEndDate(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)));
        }

        return transactionRepository.findByTransactionDateBetween(transactionFilter.getFromDate(), transactionFilter.getEndDate(), pageable);
    }

}
