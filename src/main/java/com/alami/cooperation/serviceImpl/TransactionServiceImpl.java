package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.controller.filter.Pagination;
import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.publisher.TransactionPublisher;
import com.alami.cooperation.repository.TransactionRepository;
import com.alami.cooperation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionPublisher transactionPublisher;

    @Override
    public List<Transaction> getTransactionList(TransactionFilter transactionFilter, Pagination pagination) {
        // TODO find all with filter and paging
        return transactionRepository.findAll();
    }

    @Override
    public void createTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setMemberId(transaction.getMemberId());
        transaction.setAmount(transaction.getAmount());
        transaction.setTransactionType(transaction.getTransactionType());
        transaction.setTransactionDate(transaction.getTransactionDate());
        transaction.setCreatedDate(new Date());
        transactionRepository.save(transaction);

        transactionPublisher.publish(transactionDto);
    }

}
