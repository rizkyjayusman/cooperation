package com.alami.cooperation.mapper;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionMapperTests {

    @Test
    public void createTransaction_shouldReturnTransaction_givenValidTransaction() throws ParseException {
        TransactionDto transaction = new TransactionDto();
        transaction.setMemberId(1L);
        transaction.setAmount(new BigDecimal(1000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);


        Transaction testedTransaction = TransactionMapper.createTransaction(transaction);
        Assertions.assertNotNull(testedTransaction);
        Assertions.assertEquals(transaction.getMemberId(), testedTransaction.getMemberId());
        Assertions.assertEquals(transaction.getTransactionType(), testedTransaction.getTransactionType());
        Assertions.assertEquals(transaction.getTransactionDate(), testedTransaction.getTransactionDate());
        Assertions.assertEquals(transaction.getAmount(), testedTransaction.getAmount());
    }
}




