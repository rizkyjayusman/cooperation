package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.TransactionHistory;
import com.alami.cooperation.repository.TransactionHistoryRepository;
import com.alami.cooperation.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public List<TransactionHistory> getTransactionHistoryList(TransactionFilter transactionFilter) {
        return transactionHistoryRepository.findAll();
    }

    @Override
    public void createTransactionHistory(TransactionDto transactionDto) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setMemberId(transactionDto.getMemberId());
        transactionHistory.setAmount(transactionDto.getAmount());
        transactionHistory.setTransactionType(transactionDto.getTransactionType());
        transactionHistory.setTransactionDate(transactionDto.getTransactionDate());
        transactionHistory.setCreatedDate(new Date());
        transactionHistoryRepository.save(transactionHistory);
    }
}
