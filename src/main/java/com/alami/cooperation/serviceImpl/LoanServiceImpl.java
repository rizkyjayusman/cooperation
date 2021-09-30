package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.repository.LoanRepository;
import com.alami.cooperation.service.LoanService;
import com.alami.cooperation.service.SavingService;
import com.alami.cooperation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SavingService savingService;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public void createLoanTransaction(TransactionDto transactionDto) {
        // TODO total savings should higher than or equal than loan amount
        if(isOverLimit(transactionDto)) {
            // TODO failed to loan a cash cause overlimit
        }

        transactionDto.setTransactionType(TransactionTypeEnum.LOAN);
        transactionService.createTransaction(transactionDto);

        Loan loan = loanRepository.getByMemberId(transactionDto.getMemberId());
        if(loan == null) {
            loan = new Loan();
            loan.setMemberId(transactionDto.getMemberId());
            loan.setAmount(new BigDecimal(0));
        }

        // TODO plus the loan amount with the last loan data

        loanRepository.save(loan);
    }

    private boolean isOverLimit(TransactionDto transactionDto) {
        BigDecimal totalSaving = savingService.getTotalSaving();
        return totalSaving.compareTo(transactionDto.getAmount()) < 0;
    }

    @Override
    public void createPayLoanTransaction(TransactionDto transactionDto) {
        // TODO total pay loan should lower than or equal than loan amount
        Loan loan = loanRepository.getByMemberId(transactionDto.getMemberId());
        if(loan == null) {
            // TODO loan does not exist
        }

        assert loan != null;
        if(isOverPay(transactionDto, loan)) {
            // TODO pay loan amount greater than the loan amount
        }

        transactionDto.setTransactionType(TransactionTypeEnum.PAY_LOAN);
        transactionService.createTransaction(transactionDto);

        // TODO subtract the loan amount with the last loan data
    }

    private boolean isOverPay(TransactionDto transactionDto, Loan loan) {
        return transactionDto.getAmount().compareTo(loan.getAmount()) > 0;
    }
}
