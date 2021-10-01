package com.alami.cooperation.policy;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Saving;

public class SavingPolicy {

    public static boolean isOverLimit(TransactionDto transactionDto, Saving saving) {
        return saving.getAmount().compareTo(transactionDto.getAmount()) < 0;
    }

}
