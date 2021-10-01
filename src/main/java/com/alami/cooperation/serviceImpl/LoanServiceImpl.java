package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.repository.LoanRepository;
import com.alami.cooperation.service.LoanService;
import com.alami.cooperation.service.SavingService;
import com.alami.cooperation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

import static com.alami.cooperation.policy.LoanPolicy.*;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SavingService savingService;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Page<Loan> getLoanList(Pageable pageable) {
        return loanRepository.findAll(pageable);
    }

    @Override
    public TransactionDto createLoanTransaction(TransactionDto transactionDto) {
        BigDecimal diffSaving = getDiffSaving(loanRepository.getTotalLoan(), savingService.getTotalSaving());
        validateLoan(transactionDto, diffSaving);

        transactionDto.setTransactionType(TransactionTypeEnum.LOAN);
        transactionService.createTransaction(transactionDto);

        Loan loan = loanRepository.getByMemberId(transactionDto.getMemberId());
        if(loan == null) {
            loan = new Loan();
            loan.setMemberId(transactionDto.getMemberId());
            loan.setAmount(new BigDecimal(0));
            loan.setCreatedDate(new Date());
        }

        addLoanAmount(loan, transactionDto);
        loan.setUpdatedDate(new Date());
        loanRepository.save(loan);
        return transactionDto;
    }

    private BigDecimal getDiffSaving(BigDecimal totalSaving, BigDecimal totalLoan) {
        return totalSaving.subtract(totalLoan);
    }

    private void validateLoan(TransactionDto transactionDto, BigDecimal totalSaving) {
        if(isOverLimit(transactionDto, totalSaving)) {
            throw new RuntimeException("loan was over limit");
        }
    }

    private void addLoanAmount(Loan loan, TransactionDto transactionDto) {
        BigDecimal amount = loan.getAmount().add(transactionDto.getAmount());
        loan.setAmount(amount);
    }

    @Override
    public void createPayLoanTransaction(TransactionDto transactionDto) {
        Loan loan = loanRepository.getByMemberId(transactionDto.getMemberId());

        validatePayLoan(transactionDto, loan);

        transactionDto.setTransactionType(TransactionTypeEnum.PAY_LOAN);
        transactionService.createTransaction(transactionDto);

        subtractLoanAmount(loan, transactionDto);
        loan.setUpdatedDate(new Date());
        loanRepository.save(loan);
    }

    private void validatePayLoan(TransactionDto transactionDto, Loan loan) {
        if(loan == null) {
            throw new RuntimeException("loan not found");
        }

        if(isPayable(loan)) {
            throw new RuntimeException("member does not has loan");
        }

        if(isOverPay(transactionDto, loan)) {
            throw new RuntimeException("pay loan amount is over pay");
        }

    }

    private void subtractLoanAmount(Loan loan, TransactionDto transactionDto) {
        BigDecimal amount = loan.getAmount().subtract(transactionDto.getAmount());
        loan.setAmount(amount);
    }
}
