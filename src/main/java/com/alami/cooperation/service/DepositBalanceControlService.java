package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;

public interface DepositBalanceControlService {
    void addBalance(Deposit deposit, TransactionDto transactionDto);

    void subtractBalance(Deposit deposit, TransactionDto transactionDto);
}
