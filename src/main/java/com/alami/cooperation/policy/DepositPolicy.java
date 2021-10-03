package com.alami.cooperation.policy;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;

public class DepositPolicy {

    public static boolean isOverLimit(TransactionDto transactionDto, Deposit deposit) {
        return deposit.getBalance().compareTo(transactionDto.getAmount()) < 0;
    }

}
