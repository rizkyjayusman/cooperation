package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.mapper.DepositMapper;
import com.alami.cooperation.repository.DepositRepository;
import com.alami.cooperation.service.DepositService;
import com.alami.cooperation.service.MemberService;
import com.alami.cooperation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class DepositServiceImpl implements DepositService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MemberService memberService;

    private final DepositRepository depositRepository;

    public DepositServiceImpl(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public TransactionDto createDepositTransaction(TransactionDto transactionDto) throws BaseException {
        Member member = memberService.getMemberById(transactionDto.getMemberId());

        transactionService.createTransaction(transactionDto);

        Deposit deposit = findByMemberId(member.getId());
        if(deposit == null) {
            deposit = DepositMapper.createDeposit(member.getId(), new BigDecimal(0));
        }

        deposit.addBalance(transactionDto);
        saveDeposit(deposit);

        return transactionDto;
    }

    @Override
    public BigDecimal getTotalDeposit() {
        return depositRepository.getTotalBalance();
    }

    @Override
    public Page<Deposit> getDepositList(Pageable pageable) {
        return depositRepository.findAll(pageable);
    }

    public Deposit findByMemberId(Long memberId) {
        return depositRepository.getByMemberId(memberId);
    }

    @Override
    public Deposit getByMemberId(Long memberId) throws BaseException {
        Deposit deposit = findByMemberId(memberId);
        if(deposit == null) {
            throw new BaseException("deposit was not found");
        }

        return deposit;
    }

    @Override
    public void saveDeposit(Deposit deposit) {
        deposit.setUpdatedDate(new Date());
        depositRepository.save(deposit);
    }
}
