package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.exception.BaseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanService {

    TransactionDto createLoanTransaction(TransactionDto transactionDto) throws BaseException;

    Page<Loan> getLoanList(Pageable pageable);

    Loan getByMemberId(Long memberId) throws BaseException;

    Loan saveLoan(Loan loan);

}
