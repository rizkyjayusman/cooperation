package com.alami.cooperation.controller;

import com.alami.cooperation.controller.filter.Pagination;
import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.controller.request.TransactionRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @GetMapping
    public void getTransactionList(@RequestBody TransactionFilter transactionFilter, @RequestBody Pagination pagination) {
        // TODO get transaction list with pagination and filtered by created_date range
        // TODO get transaction list with pagination and filtered by member id
    }

}
