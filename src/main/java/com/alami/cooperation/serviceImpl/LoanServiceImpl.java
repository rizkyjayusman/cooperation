package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.mapper.LoanMapper;
import com.alami.cooperation.publisher.TransactionPublisher;
import com.alami.cooperation.repository.LoanRepository;
import com.alami.cooperation.service.DepositService;
import com.alami.cooperation.service.LoanService;
import com.alami.cooperation.service.MemberService;
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
    private DepositService depositService;

    @Autowired
    private MemberService memberService;

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
    public TransactionDto createLoanTransaction(TransactionDto transactionDto) throws BaseException {
        Member member = memberService.getMemberById(transactionDto.getMemberId());
        if(member == null) {
            throw new BaseException("member not found");
        }

        BigDecimal diffBalance = getDiffBalance(depositService.getTotalDeposit(), getTotalLoan());
        validateLoan(transactionDto, diffBalance);

        transactionDto.setTransactionType(TransactionTypeEnum.LOAN);
        transactionService.createTransaction(transactionDto);

        Loan loan = loanRepository.getByMemberId(transactionDto.getMemberId());
        if(loan == null) {
            loan = LoanMapper.createLoan(transactionDto.getMemberId(), new BigDecimal(0));
        }

        addLoanAmount(loan, transactionDto);
        loanRepository.save(loan);
        return transactionDto;
    }

    private BigDecimal getTotalLoan() {
        BigDecimal totalLoan = loanRepository.getTotalLoan();
        if(totalLoan == null) {
            return new BigDecimal(0);
        }

        return totalLoan;
    }

    private BigDecimal getDiffBalance(BigDecimal totalDeposit, BigDecimal totalLoan) {
        return totalDeposit.subtract(totalLoan);
    }

    private void validateLoan(TransactionDto transactionDto, BigDecimal totalDeposit) throws BaseException {
        if(isOverLimit(transactionDto, totalDeposit)) {
            throw new BaseException("loan was over limit");
        }
    }

    private void addLoanAmount(Loan loan, TransactionDto transactionDto) {
        BigDecimal amount = loan.getAmount().add(transactionDto.getAmount());
        LoanMapper.updateLoan(loan, amount);
    }

    public void subtractLoanAmount(Loan loan, TransactionDto transactionDto) {
        BigDecimal amount = loan.getAmount().subtract(transactionDto.getAmount());
        LoanMapper.updateLoan(loan, amount);
    }
}
