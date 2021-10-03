package com.alami.cooperation.policy;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;

import java.math.BigDecimal;

public class LoanPolicy {

    public static boolean isOverLimit(TransactionDto transactionDto, BigDecimal totalDeposit) {
        return totalDeposit.compareTo(transactionDto.getAmount()) < 0;
    }

    public static boolean isOverPay(TransactionDto transactionDto, Loan loan) {
        return transactionDto.getAmount().compareTo(loan.getAmount()) > 0;
    }

    public static boolean isPayable(Loan loan) {
        return loan.getAmount().compareTo(new BigDecimal(0)) > 0;
    }
}
