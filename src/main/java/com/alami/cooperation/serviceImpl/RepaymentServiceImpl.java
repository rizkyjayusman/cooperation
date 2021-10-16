package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.alami.cooperation.policy.LoanPolicy.isOverPay;
import static com.alami.cooperation.policy.LoanPolicy.isPayable;

@Service
public class RepaymentServiceImpl implements RepaymentService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanAmountControlService loanAmountControlService;

    @Autowired
    private TransactionService transactionService;

    @Override
    public TransactionDto createRepaymentTransaction(TransactionDto transactionDto) throws BaseException {
        Member member = memberService.getMemberById(transactionDto.getMemberId());
        Loan loan = loanService.getByMemberId(member.getId());

        validateRepayment(transactionDto, loan);

        transactionService.createTransaction(transactionDto);

        loanAmountControlService.subtractAmount(loan, transactionDto);
        loanService.saveLoan(loan);

        return transactionDto;
    }

    private void validateRepayment(TransactionDto transactionDto, Loan loan) throws BaseException {
        if(! isPayable(loan)) {
            throw new BaseException("member does not has loan");
        }

        if(isOverPay(transactionDto, loan)) {
            throw new BaseException("repayment amount is over pay");
        }

    }
}
