package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Saving;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.repository.SavingRepository;
import com.alami.cooperation.service.SavingService;
import com.alami.cooperation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SavingServiceImpl implements SavingService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SavingRepository savingRepository;

    @Override
    public void createSavingTransaction(TransactionDto transactionDto) {
        // TODO create transaction in to main db
        transactionDto.setTransactionType(TransactionTypeEnum.SAVING);
        transactionService.createTransaction(transactionDto);

        Saving saving = savingRepository.getByMemberId(transactionDto.getMemberId());
        if(saving == null) {
            saving = new Saving();
            saving.setMemberId(transactionDto.getMemberId());
        }

        // TODO plus the saving amount with the last saving data

        savingRepository.save(saving);
    }

    @Override
    public void createDebitTransaction(TransactionDto transactionDto) {
        Saving saving = savingRepository.getByMemberId(transactionDto.getMemberId());
        if(saving == null) {
            // TODO saving not exist
        }

        // TODO saving should higher than or equal than debit amount
        if(isOverLimit(transactionDto, saving)) {
            // TODO failed to debit a saving cause overlimit
        }

        transactionDto.setTransactionType(TransactionTypeEnum.DEBIT);
        transactionService.createTransaction(transactionDto);

        // TODO subtract the saving amount with the last saving data

        savingRepository.save(saving);
    }


    private boolean isOverLimit(TransactionDto transactionDto, Saving saving) {
        return saving.getAmount().compareTo(transactionDto.getAmount()) < 0;
    }

    @Override
    public BigDecimal getTotalSaving() {
        return savingRepository.getTotalSaving();
    }
}
