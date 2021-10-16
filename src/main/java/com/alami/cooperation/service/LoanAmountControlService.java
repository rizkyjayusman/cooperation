package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;

public interface LoanAmountControlService {

    void addAmount(Loan loan, TransactionDto transactionDto);

    void subtractAmount(Loan loan, TransactionDto transactionDto);

}
