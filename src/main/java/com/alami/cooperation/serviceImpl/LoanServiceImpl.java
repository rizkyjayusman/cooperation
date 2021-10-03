package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.publisher.TransactionPublisher;
import com.alami.cooperation.repository.LoanRepository;
import com.alami.cooperation.service.DepositService;
import com.alami.cooperation.service.LoanService;
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
    private TransactionPublisher transactionPublisher;

    @Autowired
    private DepositService depositService;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Page<Loan> getLoanList(Pageable pageable) {
        return loanRepository.findAll(pageable);
    }

    @Override
    public Loan getByMemberId(Long memberId) {
        return loanRepository.getByMemberId(memberId);
    }

    @Override
    public Loan saveLoan(Loan loan) {
        loan.setUpdatedDate(new Date());
        return loanRepository.save(loan);
    }

    @Override
    public TransactionDto createLoanTransaction(TransactionDto transactionDto) {
        BigDecimal diffBalance = getDiffBalance(loanRepository.getTotalLoan(), depositService.getTotalDeposit());
        validateLoan(transactionDto, diffBalance);

        transactionDto.setTransactionType(TransactionTypeEnum.LOAN);
        transactionPublisher.publish(transactionDto);

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

    private BigDecimal getDiffBalance(BigDecimal totalDeposit, BigDecimal totalLoan) {
        return totalDeposit.subtract(totalLoan);
    }

    private void validateLoan(TransactionDto transactionDto, BigDecimal totalDeposit) {
        if(isOverLimit(transactionDto, totalDeposit)) {
            throw new RuntimeException("loan was over limit");
        }
    }

    private void addLoanAmount(Loan loan, TransactionDto transactionDto) {
        BigDecimal amount = loan.getAmount().add(transactionDto.getAmount());
        loan.setAmount(amount);
    }

    public void subtractLoanAmount(Loan loan, TransactionDto transactionDto) {
        BigDecimal amount = loan.getAmount().subtract(transactionDto.getAmount());
        loan.setAmount(amount);
    }
}
