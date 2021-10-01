package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Saving;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface SavingService {

    TransactionDto createSavingTransaction(TransactionDto transactionDto);

    TransactionDto createDebitTransaction(TransactionDto transactionDto);

    BigDecimal getTotalSaving();

    Page<Saving> getSavingList(Pageable pageable);
}
