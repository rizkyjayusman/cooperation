package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanService {

    TransactionDto createLoanTransaction(TransactionDto transactionDto);

    void createPayLoanTransaction(TransactionDto transactionDto);

    Page<Loan> getLoanList(Pageable pageable);
}
