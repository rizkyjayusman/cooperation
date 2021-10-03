package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.repository.TransactionRepository;
import com.alami.cooperation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public void createTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setMemberId(transactionDto.getMemberId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setTransactionDate(transactionDto.getTransactionDate());
        transaction.setCreatedDate(new Date());
        transactionRepository.save(transaction);
    }

}
