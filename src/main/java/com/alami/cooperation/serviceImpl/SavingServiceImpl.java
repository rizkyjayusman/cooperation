package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Saving;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.repository.SavingRepository;
import com.alami.cooperation.service.SavingService;
import com.alami.cooperation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

import static com.alami.cooperation.policy.SavingPolicy.isOverLimit;

@Service
public class SavingServiceImpl implements SavingService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SavingRepository savingRepository;

    @Override
    public Page<Saving> getSavingList(Pageable pageable) {
        return savingRepository.findAll(pageable);
    }

    @Override
    public TransactionDto createSavingTransaction(TransactionDto transactionDto) {
        transactionDto.setTransactionType(TransactionTypeEnum.SAVING);
        transactionService.createTransaction(transactionDto);

        Saving saving = savingRepository.getByMemberId(transactionDto.getMemberId());
        if(saving == null) {
            saving = new Saving();
            saving.setMemberId(transactionDto.getMemberId());
            saving.setAmount(transactionDto.getAmount());
            saving.setCreatedDate(new Date());
            saving.setAmount(new BigDecimal(0));
        }

        BigDecimal amount = saving.getAmount().add(transactionDto.getAmount());
        saving.setAmount(amount);
        saving.setUpdatedDate(new Date());
        savingRepository.save(saving);
        return transactionDto;
    }

    @Override
    public TransactionDto createDebitTransaction(TransactionDto transactionDto) {
        Saving saving = savingRepository.getByMemberId(transactionDto.getMemberId());
        if(saving == null) {
            throw new RuntimeException("saving was not found");
        }

        if(isOverLimit(transactionDto, saving)) {
            throw new RuntimeException("debit saving over limit");
        }

        transactionDto.setTransactionType(TransactionTypeEnum.DEBIT);
        transactionService.createTransaction(transactionDto);

        BigDecimal amount = saving.getAmount().subtract(transactionDto.getAmount());
        saving.setAmount(amount);
        savingRepository.save(saving);
        return transactionDto;
    }

    @Override
    public BigDecimal getTotalSaving() {
        return savingRepository.getTotalSaving();
    }
}
