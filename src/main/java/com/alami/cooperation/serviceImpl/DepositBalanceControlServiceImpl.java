package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.service.DepositBalanceControlService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepositBalanceControlServiceImpl implements DepositBalanceControlService {

    public void addBalance(Deposit deposit, TransactionDto transactionDto) {
        BigDecimal balance = deposit.getBalance().add(transactionDto.getAmount());
        deposit.setBalance(balance);
    }

    public void subtractBalance(Deposit deposit, TransactionDto transactionDto) {
        BigDecimal balance = deposit.getBalance().subtract(transactionDto.getAmount());
        deposit.setBalance(balance);
    }
}
