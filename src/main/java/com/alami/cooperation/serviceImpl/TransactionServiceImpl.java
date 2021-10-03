package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.repository.TransactionRepository;
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
    private TransactionRepository transactionRepository;

    @Override
    public Page<Transaction> getTransactionList(Pageable pageable) {
        // TODO find all with filter and paging
        return transactionRepository.findAll(pageable);
    }

    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setMemberId(transactionDto.getMemberId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setTransactionDate(transactionDto.getTransactionDate());
        transaction.setCreatedDate(new Date());
        return transactionRepository.save(transaction);
    }

    @Override
    public Page<Transaction> getTransactionList(TransactionFilter transactionFilter, Pageable pageable) {
        if(transactionFilter.getMemberId() != null) {
            return transactionRepository.findByMemberId(transactionFilter.getMemberId(), pageable);
        }
//
        if(transactionFilter.getFromDate() == null) {
            transactionFilter.setFromDate(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(3)));
        }

        if(transactionFilter.getEndDate() == null) {
            transactionFilter.setFromDate(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)));
        }

//        return transactionRepository.findAll(pageable);
        return transactionRepository.findAByTransactionDateBetween(transactionFilter.getFromDate(), transactionFilter.getEndDate(), pageable);
    }

}
