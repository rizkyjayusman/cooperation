package com.alami.cooperation.mapper;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.TransactionHistory;

import java.util.Date;

public class TransactionHistoryMapper {

    public static TransactionHistory createTransactionHistory(TransactionDto transactionDto) {
        TransactionHistory transaction = new TransactionHistory();
        transaction.setMemberId(transactionDto.getMemberId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setTransactionDate(transactionDto.getTransactionDate());
        transaction.setCreatedDate(new Date());
        return transaction;
    }

}
