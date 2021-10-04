package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.publisher.TransactionPublisher;
import com.alami.cooperation.service.DepositService;
import com.alami.cooperation.service.MemberService;
import com.alami.cooperation.service.TransactionService;
import com.alami.cooperation.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
        if(member == null) {
            throw new BaseException("member not found");
        }

        Deposit deposit = depositService.getByMemberId(transactionDto.getMemberId());
        if(deposit == null) {
            throw new BaseException("deposit was not found");
        }

        if(isOverLimit(transactionDto, deposit)) {
            throw new BaseException("withdrawal deposit over limit");
        }

        transactionDto.setTransactionType(TransactionTypeEnum.WITHDRAWAL);
        transactionService.createTransaction(transactionDto);

        depositService.subtractBalance(deposit, transactionDto);

        depositService.saveDeposit(deposit);
        return transactionDto;
    }
}
