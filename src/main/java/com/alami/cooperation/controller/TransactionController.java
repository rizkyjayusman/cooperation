package com.alami.cooperation.controller;

import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.service.TransactionService;
import com.alami.cooperation.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Object> getTransactionList(Pageable pageable) {
        // TODO get transaction list with pagination and filtered by created_date range
        // TODO get transaction list with pagination and filtered by member id

        Page<Transaction> page = transactionService.getTransactionList(pageable);
        Pagination pagination = new Pagination(page.getContent(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

}
