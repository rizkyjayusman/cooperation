package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.publisher.TransactionPublisher;
import com.alami.cooperation.service.LoanService;
import com.alami.cooperation.service.MemberService;
import com.alami.cooperation.service.RepaymentService;
import com.alami.cooperation.service.TransactionService;
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
    private TransactionService transactionService;

    @Override
    public TransactionDto createRepaymentTransaction(TransactionDto transactionDto) throws BaseException {
        Member member = memberService.getMemberById(transactionDto.getMemberId());
        if(member == null) {
            throw new BaseException("member not found");
        }

        Loan loan = loanService.getByMemberId(transactionDto.getMemberId());

        validateRepayment(transactionDto, loan);

        transactionDto.setTransactionType(TransactionTypeEnum.REPAYMENT);
        transactionService.createTransaction(transactionDto);

        loanService.subtractLoanAmount(loan, transactionDto);
        loanService.saveLoan(loan);
        return transactionDto;
    }

    private void validateRepayment(TransactionDto transactionDto, Loan loan) throws BaseException {
        if(loan == null) {
            throw new BaseException("loan not found");
        }

        if(! isPayable(loan)) {
            throw new BaseException("member does not has loan");
        }

        if(isOverPay(transactionDto, loan)) {
            throw new BaseException("repayment amount is over pay");
        }

    }
}
