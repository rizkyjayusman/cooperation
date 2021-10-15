package com.alami.cooperation.mapper;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.enumtype.TransactionTypeEnum;

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

    public static TransactionDto createTransactionDto(TransactionRequest transactionRequest, TransactionTypeEnum transactionType) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transactionRequest.getAmount());
        transactionDto.setTransactionDate(transactionRequest.getTransactionDate());
        transactionDto.setTransactionType(transactionType);
        transactionDto.setMemberId(transactionRequest.getMemberId());
        return transactionDto;
    }

}
