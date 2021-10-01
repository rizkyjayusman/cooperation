package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Saving;
import com.alami.cooperation.service.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("savings")
public class SavingController {

    @Autowired
    private SavingService savingService;

    @GetMapping
    public Page<Saving> getSavingList(Pageable pageable) {
        return savingService.getSavingList(pageable);
    }

    @PostMapping
    public void createSavingTransaction(@Validated @RequestBody TransactionRequest transactionRequest) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transactionRequest.getAmount());
        transactionDto.setTransactionDate(transactionRequest.getTransactionDate());
        transactionDto.setMemberId(transactionRequest.getMemberId());
        savingService.createSavingTransaction(transactionDto);
    }

    @PostMapping("/debit")
    public void createDebitTransaction(@Validated @RequestBody TransactionRequest transactionRequest) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transactionRequest.getAmount());
        transactionDto.setTransactionDate(transactionRequest.getTransactionDate());
        transactionDto.setMemberId(transactionRequest.getMemberId());
        savingService.createDebitTransaction(transactionDto);
    }

}
