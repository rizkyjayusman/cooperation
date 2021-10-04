package com.alami.cooperation.dto;

import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionDtoTests {

    @Test
    public void createTransactionDto_shouldReturnTransaction_givenValidTransactionDto() throws ParseException {
        TransactionDto transaction = new TransactionDto();
        transaction.setMemberId(1L);
        transaction.setAmount(new BigDecimal(1000));
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);
        Date transactionDate = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-10");
        transaction.setTransactionDate(transactionDate);

        Assertions.assertNotNull(transaction);
        Assertions.assertEquals(1L, transaction.getMemberId());
        Assertions.assertEquals(new BigDecimal(1000), transaction.getAmount());
        Assertions.assertEquals(TransactionTypeEnum.DEPOSIT, transaction.getTransactionType());
        Assertions.assertEquals(transactionDate, transaction.getTransactionDate());
    }

}
