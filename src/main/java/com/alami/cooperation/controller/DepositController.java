package com.alami.cooperation.controller;

import com.alami.cooperation.controller.filter.PaginationFilter;
import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.controller.response.Response;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.service.DepositService;
import com.alami.cooperation.vo.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.alami.cooperation.handler.ResponseHandler.success;

@Api(tags = "Deposit Endpoints")
@RestController
@RequestMapping("deposits")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @ApiOperation(value = "Get All Deposit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "get all deposit successfully", response = Response.class),
    })
    @GetMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> getDepositList(PaginationFilter paginationFilter) {
        PageRequest pageable = PageRequest.of(paginationFilter.getPage(), paginationFilter.getSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<Deposit> page = depositService.getDepositList(pageable);
        Pagination pagination = new Pagination(page.getContent(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

    @ApiOperation(value = "Create Deposit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "create deposit successfully", response = Response.class),
            @ApiResponse(code = 400, message = "invalid parameter", response = Response.class),
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> createDepositTransaction(@Validated @RequestBody TransactionRequest transactionRequest) throws BaseException {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transactionRequest.getAmount());
        transactionDto.setTransactionDate(transactionRequest.getTransactionDate());
        transactionDto.setMemberId(transactionRequest.getMemberId());
        depositService.createDepositTransaction(transactionDto);
        return success("create deposit successfully", null, HttpStatus.OK);
    }
}
