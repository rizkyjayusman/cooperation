package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("withdrawals")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;

    @PostMapping
    public void createWithdrawalTransaction(@Validated @RequestBody TransactionRequest transactionRequest) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transactionRequest.getAmount());
        transactionDto.setTransactionDate(transactionRequest.getTransactionDate());
        transactionDto.setMemberId(transactionRequest.getMemberId());
        withdrawalService.createWithdrawalTransaction(transactionDto);
    }

}
