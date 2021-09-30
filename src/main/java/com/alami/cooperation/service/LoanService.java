package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;

public interface LoanService {

    void createLoanTransaction(TransactionDto transactionDto);

    void createPayLoanTransaction(TransactionDto transactionDto);

}
