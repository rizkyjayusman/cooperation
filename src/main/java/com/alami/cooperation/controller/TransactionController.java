package com.alami.cooperation.controller;

import com.alami.cooperation.controller.filter.PaginationFilter;
import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.service.TransactionService;
import com.alami.cooperation.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Object> getTransactionList(TransactionFilter transactionFilter, PaginationFilter paginationFilter) {
        PageRequest pageable = PageRequest.of(paginationFilter.getPage(), paginationFilter.getSize(), Sort.by(Sort.Direction.DESC, "transaction_date"));
        Page<Transaction> page = transactionService.getTransactionList(transactionFilter, pageable);
        Pagination pagination = new Pagination(page.getContent(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

}
