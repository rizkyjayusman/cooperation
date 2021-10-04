package com.alami.cooperation.mapper;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;

import java.util.Date;

public class TransactionMapper {

    public static Transaction createTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setMemberId(transactionDto.getMemberId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setTransactionDate(transactionDto.getTransactionDate());
        transaction.setCreatedDate(new Date());
        return transaction;
    }

}
