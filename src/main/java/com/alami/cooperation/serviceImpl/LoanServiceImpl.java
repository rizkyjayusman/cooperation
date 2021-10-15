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

    private Loan findByMemberId(Long memberId) {
        return loanRepository.getByMemberId(memberId);
    }

    @Override
    public Loan getByMemberId(Long memberId) throws BaseException {
        Loan loan = findByMemberId(memberId);
        if(loan == null) {
            throw new BaseException("loan not found");
        }

        return loan;
    }

    @Override
    public Loan saveLoan(Loan loan) {
        loan.setUpdatedDate(new Date());
        return loanRepository.save(loan);
    }

    @Override
    public TransactionDto createLoanTransaction(TransactionDto transactionDto) throws BaseException {
        Member member = memberService.getMemberById(transactionDto.getMemberId());
        BigDecimal diffBalance = getDiffBalance(depositService.getTotalDeposit(), getTotalLoan());
        validateLoan(transactionDto, diffBalance);

        transactionService.createTransaction(transactionDto);

        Loan loan = findByMemberId(member.getId());
        if(loan == null) {
            loan = LoanMapper.createLoan(member.getId(), new BigDecimal(0));
        }

        loan.addAmount(transactionDto);
        saveLoan(loan);

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
}
