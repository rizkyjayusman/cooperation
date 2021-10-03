package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.service.DepositService;
import com.alami.cooperation.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.alami.cooperation.handler.ResponseHandler.success;

@RestController
@RequestMapping("deposits")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @GetMapping
    public ResponseEntity<Object> getDepositList(Pageable pageable) {
        Page<Deposit> page = depositService.getDepositList(pageable);
        Pagination pagination = new Pagination(page.getContent(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createDepositTransaction(@Validated @RequestBody TransactionRequest transactionRequest) throws BaseException {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transactionRequest.getAmount());
        transactionDto.setTransactionDate(transactionRequest.getTransactionDate());
        transactionDto.setMemberId(transactionRequest.getMemberId());
        depositService.createDepositTransaction(transactionDto);
        return success("create deposit successfully", null, HttpStatus.OK);
    }
}
