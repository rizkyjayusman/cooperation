package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.alami.cooperation.util.ResponseHandler.success;

@RestController
@RequestMapping("withdrawals")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;

    @PostMapping
    public ResponseEntity<Object> createWithdrawalTransaction(@Validated @RequestBody TransactionRequest transactionRequest) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transactionRequest.getAmount());
        transactionDto.setTransactionDate(transactionRequest.getTransactionDate());
        transactionDto.setMemberId(transactionRequest.getMemberId());
        withdrawalService.createWithdrawalTransaction(transactionDto);
        return success("create withdrawal successfully", null, HttpStatus.OK);
    }

}
