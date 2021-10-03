package com.alami.cooperation.policy;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class DepositPolicyTests {

    @Test
    public void isOverLimit_shouldReturnTrue_givenTransactionAmountGreaterThanBalance() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1500000));
        transactionDto.setTransactionType(TransactionTypeEnum.WITHDRAWAL);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setMemberId(1L);
        deposit.setBalance(new BigDecimal(1000000));
        deposit.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        deposit.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertTrue(DepositPolicy.isOverLimit(transactionDto, deposit));
    }

    @Test
    public void isOverLimit_shouldReturnFalse_givenTransactionAmountEqualThanBalance() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.WITHDRAWAL);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setMemberId(1L);
        deposit.setBalance(new BigDecimal(1000000));
        deposit.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        deposit.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertFalse(DepositPolicy.isOverLimit(transactionDto, deposit));
    }

    @Test
    public void isOverLimit_shouldReturnFalse_givenTransactionAmountGreaterThanBalance() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(500000));
        transactionDto.setTransactionType(TransactionTypeEnum.WITHDRAWAL);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setMemberId(1L);
        deposit.setBalance(new BigDecimal(1000000));
        deposit.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        deposit.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertFalse(DepositPolicy.isOverLimit(transactionDto, deposit));
    }

}
