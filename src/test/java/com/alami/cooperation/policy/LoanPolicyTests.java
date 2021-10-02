package com.alami.cooperation.policy;


import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class LoanPolicyTests {

    @Test
    public void isOverLimit_shouldReturnTrue_givenTransactionAmountGreaterThanTotalSaving() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.LOAN);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        BigDecimal totalSaving = new BigDecimal(500000);
        Assertions.assertTrue(LoanPolicy.isOverLimit(transactionDto, totalSaving));
    }

    @Test
    public void isOverLimit_shouldReturnFalse_givenTransactionAmountLessThanTotalSaving() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.LOAN);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        BigDecimal totalSaving = new BigDecimal(1500000);
        Assertions.assertFalse(LoanPolicy.isOverLimit(transactionDto, totalSaving));
    }

    @Test
    public void isOverLimit_shouldReturnFalse_givenTransactionAmountEqualsThanLoanAmount() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.LOAN);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        Loan loan = new Loan();
        loan.setId(1L);
        loan.setMemberId(1L);
        loan.setAmount(new BigDecimal(1000000));
        loan.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        loan.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertFalse(LoanPolicy.isOverPay(transactionDto, loan));
    }


    @Test
    public void isOverPay_shouldReturnTrue_givenTransactionAmountGreaterThanLoanAmount() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.LOAN);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        Loan loan = new Loan();
        loan.setId(1L);
        loan.setMemberId(1L);
        loan.setAmount(new BigDecimal(900000));
        loan.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        loan.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertTrue(LoanPolicy.isOverPay(transactionDto, loan));
    }

    @Test
    public void isOverPay_shouldReturnFalse_givenTransactionAmountEqualsThanLoanAmount() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.LOAN);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        Loan loan = new Loan();
        loan.setId(1L);
        loan.setMemberId(1L);
        loan.setAmount(new BigDecimal(1000000));
        loan.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        loan.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertFalse(LoanPolicy.isOverPay(transactionDto, loan));
    }

    @Test
    public void isOverPay_shouldReturnFalse_givenTransactionAmountLessThanLoanAmount() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(500000));
        transactionDto.setTransactionType(TransactionTypeEnum.LOAN);
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));

        Loan loan = new Loan();
        loan.setId(1L);
        loan.setMemberId(1L);
        loan.setAmount(new BigDecimal(1000000));
        loan.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        loan.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertFalse(LoanPolicy.isOverPay(transactionDto, loan));
    }

    @Test
    public void isPayable_shouldReturnTrue_givenLoanAmountGreaterThanZero() throws Exception {
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setMemberId(1L);
        loan.setAmount(new BigDecimal(1000000));
        loan.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        loan.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertTrue(LoanPolicy.isPayable(loan));
    }

    @Test
    public void isPayable_shouldReturnFalse_givenLoanAmountEqualsThanZero() throws Exception {
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setMemberId(1L);
        loan.setAmount(new BigDecimal(0));
        loan.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        loan.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));

        Assertions.assertFalse(LoanPolicy.isPayable(loan));
    }


}
