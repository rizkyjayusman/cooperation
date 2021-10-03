package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface DepositService {

    TransactionDto createDepositTransaction(TransactionDto transactionDto);

    BigDecimal getTotalDeposit();

    Page<Deposit> getDepositList(Pageable pageable);

    Deposit getByMemberId(Long memberId);

    void saveDeposit(Deposit deposit);

    void subtractBalance(Deposit deposit, TransactionDto transactionDto);
}
