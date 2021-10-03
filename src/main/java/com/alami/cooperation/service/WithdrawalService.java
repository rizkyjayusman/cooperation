package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;

public interface WithdrawalService {

    TransactionDto createWithdrawalTransaction(TransactionDto transactionDto);

}
