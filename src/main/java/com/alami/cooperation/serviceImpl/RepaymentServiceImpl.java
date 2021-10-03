package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.publisher.TransactionPublisher;
import com.alami.cooperation.service.LoanService;
import com.alami.cooperation.service.MemberService;
import com.alami.cooperation.service.RepaymentService;
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
    private TransactionPublisher transactionPublisher;

    @Override
    public TransactionDto createRepaymentTransaction(TransactionDto transactionDto) {
        Member member = memberService.getMemberById(transactionDto.getMemberId());
        if(member == null) {
            throw new RuntimeException("member not found");
        }

        Loan loan = loanService.getByMemberId(transactionDto.getMemberId());

        validateRepayment(transactionDto, loan);

        transactionDto.setTransactionType(TransactionTypeEnum.REPAYMENT);
        transactionPublisher.publish(transactionDto);

        loanService.subtractLoanAmount(loan, transactionDto);
        loanService.saveLoan(loan);
        return transactionDto;
    }

    private void validateRepayment(TransactionDto transactionDto, Loan loan) {
        if(loan == null) {
            throw new RuntimeException("loan not found");
        }

        if(! isPayable(loan)) {
            throw new RuntimeException("member does not has loan");
        }

        if(isOverPay(transactionDto, loan)) {
            throw new RuntimeException("repayment amount is over pay");
        }

    }
}
