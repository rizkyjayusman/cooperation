package com.alami.cooperation.controller;

import com.alami.cooperation.controller.filter.PaginationFilter;
import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.controller.response.Response;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.mapper.TransactionMapper;
import com.alami.cooperation.service.LoanService;
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

@Api(tags = "Loan Endpoints")
@RestController
@RequestMapping("loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @ApiOperation(value = "Get All Loan")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "get all loan successfully", response = Response.class),
    })
    @GetMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> getLoanList(PaginationFilter paginationFilter) {
        PageRequest pageable = PageRequest.of(paginationFilter.getPage(), paginationFilter.getSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<Loan> page = loanService.getLoanList(pageable);
        Pagination pagination = new Pagination(page.getContent(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

    @ApiOperation(value = "Create Loan")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "create loan successfully", response = Response.class),
            @ApiResponse(code = 400, message = "invalid parameter", response = Response.class),
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> createLoanTransaction(@Validated @RequestBody TransactionRequest transactionRequest) throws BaseException {
        TransactionDto transactionDto = TransactionMapper.createTransactionDto(transactionRequest, TransactionTypeEnum.LOAN);
        loanService.createLoanTransaction(transactionDto);
        return success("create loan successfully", null, HttpStatus.OK);
    }
}
