package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.publisher.TransactionPublisher;
import com.alami.cooperation.repository.DepositRepository;
import com.alami.cooperation.service.DepositService;
import com.alami.cooperation.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class DepositServiceImpl implements DepositService {

    @Autowired
    private TransactionPublisher transactionPublisher;

    @Autowired
    private MemberService memberService;

    private final DepositRepository depositRepository;

    public DepositServiceImpl(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public TransactionDto createDepositTransaction(TransactionDto transactionDto) {
        transactionDto.setTransactionType(TransactionTypeEnum.DEPOSIT);
        transactionPublisher.publish(transactionDto);

        Member member = memberService.getMemberById(transactionDto.getMemberId());
        if(member == null) {
            throw new RuntimeException("member not found");
        }

        Deposit deposit = depositRepository.getByMemberId(transactionDto.getMemberId());
        if(deposit == null) {
            deposit = new Deposit();
            deposit.setMemberId(transactionDto.getMemberId());
            deposit.setCreatedDate(new Date());
            deposit.setBalance(new BigDecimal(0));
        }

        addDepositBalance(deposit, transactionDto);
        deposit.setUpdatedDate(new Date());
        depositRepository.save(deposit);
        return transactionDto;
    }

    public void addDepositBalance(Deposit deposit, TransactionDto transactionDto) {
        BigDecimal balance = deposit.getBalance().add(transactionDto.getAmount());
        deposit.setBalance(balance);
    }

    @Override
    public BigDecimal getTotalDeposit() {
        return depositRepository.getTotalBalance();
    }

    @Override
    public Page<Deposit> getDepositList(Pageable pageable) {
        return depositRepository.findAll(pageable);
    }

    @Override
    public Deposit getByMemberId(Long memberId) {
        return depositRepository.getByMemberId(memberId);
    }

    @Override
    public void saveDeposit(Deposit deposit) {
        deposit.setUpdatedDate(new Date());
        depositRepository.save(deposit);
    }

    @Override
    public void subtractBalance(Deposit deposit, TransactionDto transactionDto) {
        BigDecimal balance = deposit.getBalance().subtract(transactionDto.getAmount());
        deposit.setBalance(balance);
    }
}
