package com.alami.cooperation.entity;

import com.alami.cooperation.enumtype.TransactionTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionHistoryTests {

    @Test
    public void createTransactionHistory_shouldReturnTransactionHistory_givenValidTransactionHistory() throws ParseException {
        TransactionHistory transaction = new TransactionHistory();
        transaction.setId("transaction_id_generated");
        transaction.setMemberId(1L);
        transaction.setAmount(new BigDecimal(1000));
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);
        Date date = new Date();
        transaction.setCreatedDate(date);
        Date transactionDate = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-10");
        transaction.setTransactionDate(transactionDate);

        Assertions.assertNotNull(transaction);
        Assertions.assertEquals("transaction_id_generated", transaction.getId());
        Assertions.assertEquals(1L, transaction.getMemberId());
        Assertions.assertEquals(new BigDecimal(1000), transaction.getAmount());
        Assertions.assertEquals(TransactionTypeEnum.DEPOSIT, transaction.getTransactionType());
        Assertions.assertEquals(transactionDate, transaction.getTransactionDate());
        Assertions.assertEquals(date, transaction.getCreatedDate());
    }

}
