package com.alami.cooperation.policy;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Saving;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class SavingPolicyTests {

    @Test
    public void isOverLimit_shouldReturnTrue_givenTransactionAmountGreaterThanSavingAmount() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1500000));
        transactionDto.setTransactionType(TransactionTypeEnum.DEBIT);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        Saving saving = new Saving();
        saving.setId(1L);
        saving.setMemberId(1L);
        saving.setAmount(new BigDecimal(1000000));
        saving.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        saving.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertTrue(SavingPolicy.isOverLimit(transactionDto, saving));
    }

    @Test
    public void isOverLimit_shouldReturnFalse_givenTransactionAmountEqualThanSavingAmount() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.DEBIT);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        Saving saving = new Saving();
        saving.setId(1L);
        saving.setMemberId(1L);
        saving.setAmount(new BigDecimal(1000000));
        saving.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        saving.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertFalse(SavingPolicy.isOverLimit(transactionDto, saving));
    }

    @Test
    public void isOverLimit_shouldReturnFalse_givenTransactionAmountGreaterThanSavingAmount() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(500000));
        transactionDto.setTransactionType(TransactionTypeEnum.DEBIT);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        Saving saving = new Saving();
        saving.setId(1L);
        saving.setMemberId(1L);
        saving.setAmount(new BigDecimal(1000000));
        saving.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        saving.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertFalse(SavingPolicy.isOverLimit(transactionDto, saving));
    }

}
