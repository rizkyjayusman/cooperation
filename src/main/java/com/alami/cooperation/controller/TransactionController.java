package com.alami.cooperation.controller;

import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Page<Transaction> getTransactionList(@RequestBody TransactionFilter transactionFilter, Pageable pageable) {
        // TODO get transaction list with pagination and filtered by created_date range
        // TODO get transaction list with pagination and filtered by member id
        return transactionService.getTransactionList(pageable);
    }

}
