package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;

public interface RepaymentService {

    TransactionDto createRepaymentTransaction(TransactionDto transactionDto);

}
