package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingController {

    // create saving transaction
    @PostMapping("/savings")
    public void createSavingTransaction(@RequestBody TransactionRequest transactionRequest) {}

    // create debit transaction
    @PostMapping()
    public void createDebitTransaction(@RequestBody TransactionRequest transactionRequest) {}

}
