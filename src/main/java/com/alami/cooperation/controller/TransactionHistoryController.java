package com.alami.cooperation.controller;

import com.alami.cooperation.controller.filter.PaginationFilter;
import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.controller.response.Response;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.entity.TransactionHistory;
import com.alami.cooperation.service.TransactionHistoryService;
import com.alami.cooperation.service.TransactionService;
import com.alami.cooperation.vo.Pagination;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions/histories")
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @ApiOperation(value = "Get All Transaction Histories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "get all transaction history successfully", response = Response.class),
    })
    @GetMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> getTransactionList(TransactionFilter transactionFilter, PaginationFilter paginationFilter) {
        PageRequest pageable = PageRequest.of(paginationFilter.getPage(), paginationFilter.getSize(), Sort.by(Sort.Direction.DESC, "transaction_date"));
        Page<TransactionHistory> page = transactionHistoryService.getTransactionHistoryList(transactionFilter, pageable);
        Pagination pagination = new Pagination(page.getContent(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

}
