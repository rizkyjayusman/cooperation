package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.exception.BaseException;

public interface RepaymentService {

    TransactionDto createRepaymentTransaction(TransactionDto transactionDto) throws BaseException;

}
