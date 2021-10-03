package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("deposits")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @GetMapping
    public Page<Deposit> getDepositList(Pageable pageable) {
        return depositService.getDepositList(pageable);
    }

    @PostMapping
    public void createDepositTransaction(@Validated @RequestBody TransactionRequest transactionRequest) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transactionRequest.getAmount());
        transactionDto.setTransactionDate(transactionRequest.getTransactionDate());
        transactionDto.setMemberId(transactionRequest.getMemberId());
        depositService.createDepositTransaction(transactionDto);
    }
}
