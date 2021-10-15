package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.service.DepositService;
import com.alami.cooperation.service.MemberService;
import com.alami.cooperation.service.TransactionService;
import com.alami.cooperation.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.alami.cooperation.policy.DepositPolicy.isOverLimit;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TransactionService transactionService;

    private final DepositService depositService;

    public WithdrawalServiceImpl(DepositService depositService) {
        this.depositService = depositService;
    }


    @Override
    public TransactionDto createWithdrawalTransaction(TransactionDto transactionDto) throws BaseException {
        Member member = memberService.getMemberById(transactionDto.getMemberId());
        Deposit deposit = depositService.getByMemberId(member.getId());

        validateWithdrawalTransaction(transactionDto, deposit);

        transactionService.createTransaction(transactionDto);

        deposit.subtractBalance(transactionDto);
        depositService.saveDeposit(deposit);

        return transactionDto;
    }

    private void validateWithdrawalTransaction(TransactionDto transactionDto, Deposit deposit) throws BaseException {
        if(isOverLimit(transactionDto, deposit)) {
            throw new BaseException("withdrawal deposit over limit");
        }
    }
}
