package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.alami.cooperation.util.ResponseHandler.success;

@RestController
@RequestMapping("loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public Page<Loan> getLoanList(Pageable pageable) {
        return loanService.getLoanList(pageable);
    }


    @PostMapping
    public ResponseEntity<Object> createLoanTransaction(@Validated @RequestBody TransactionRequest transactionRequest) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transactionRequest.getAmount());
        transactionDto.setTransactionDate(transactionRequest.getTransactionDate());
        transactionDto.setMemberId(transactionRequest.getMemberId());
        loanService.createLoanTransaction(transactionDto);
        return success("create loan successfully", null, HttpStatus.OK);
    }
}
