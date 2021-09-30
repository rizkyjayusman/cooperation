package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    // create loan transaction
    @PostMapping
    public void createLoanTransaction(@RequestBody TransactionRequest transactionRequest) {}

    // create pay the loan transaction
    @PostMapping
    public void createPayLoanTransaction(@RequestBody TransactionRequest transactionRequest) {}


}
