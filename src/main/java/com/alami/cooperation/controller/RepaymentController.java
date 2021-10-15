package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.controller.response.Response;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.mapper.TransactionMapper;
import com.alami.cooperation.service.RepaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alami.cooperation.handler.ResponseHandler.success;

@Api(tags = "Repayment Endpoints")
@RestController
@RequestMapping("repayments")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    @ApiOperation(value = "Create Repayment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "create repayment successfully", response = Response.class),
            @ApiResponse(code = 400, message = "invalid parameter", response = Response.class),
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> createRepaymentTransaction(@Validated @RequestBody TransactionRequest transactionRequest) throws BaseException {
        TransactionDto transactionDto = TransactionMapper.createTransactionDto(transactionRequest, TransactionTypeEnum.REPAYMENT);
        repaymentService.createRepaymentTransaction(transactionDto);
        return success("create repayment successfully", null, HttpStatus.OK);
    }

}
