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

import static com.alami.cooperation.policy.LoanPolicy.isOverLimit;
import static com.alami.cooperation.policy.LoanPolicy.isOverPay;

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
    public void createLoanTransaction(TransactionDto transactionDto) {
        BigDecimal totalLoan = loanRepository.getTotalLoan();
        BigDecimal totalSaving = savingService.getTotalSaving().subtract(totalLoan);
        if(isOverLimit(transactionDto, totalSaving)) {
            throw new RuntimeException("loan was over limit");
        }

        transactionDto.setTransactionType(TransactionTypeEnum.LOAN);
        transactionService.createTransaction(transactionDto);

        Loan loan = loanRepository.getByMemberId(transactionDto.getMemberId());
        if(loan == null) {
            loan = new Loan();
            loan.setMemberId(transactionDto.getMemberId());
            loan.setAmount(new BigDecimal(0));
        }

        BigDecimal amount = loan.getAmount().add(transactionDto.getAmount());
        loan.setAmount(amount);
        loanRepository.save(loan);
    }

    @Override
    public void createPayLoanTransaction(TransactionDto transactionDto) {
        // TODO total pay loan should lower than or equal than loan amount
        Loan loan = loanRepository.getByMemberId(transactionDto.getMemberId());
        if(loan == null) {
            throw new RuntimeException("loan not found");
        }

        if(loan.getAmount().compareTo(new BigDecimal(0)) > 0) {
            throw new RuntimeException("member does not has loan");
        }

        if(isOverPay(transactionDto, loan)) {
            throw new RuntimeException("pay loan amount is over pay");
        }

        transactionDto.setTransactionType(TransactionTypeEnum.PAY_LOAN);
        transactionService.createTransaction(transactionDto);

        BigDecimal amount = loan.getAmount().subtract(transactionDto.getAmount());
        loan.setAmount(amount);
        loanRepository.save(loan);
    }
}
