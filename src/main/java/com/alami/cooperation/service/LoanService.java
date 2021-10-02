package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanService {

    TransactionDto createLoanTransaction(TransactionDto transactionDto);

    Page<Loan> getLoanList(Pageable pageable);

    Loan getByMemberId(Long memberId);

    Loan saveLoan(Loan loan);

    void subtractLoanAmount(Loan loan, TransactionDto transactionDto);
}
