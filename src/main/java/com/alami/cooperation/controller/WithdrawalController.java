package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.controller.response.Response;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.mapper.TransactionMapper;
import com.alami.cooperation.service.WithdrawalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.alami.cooperation.handler.ResponseHandler.success;

@Api(tags = "Withdrawal Endpoints")
@RestController
@RequestMapping("withdrawals")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;

    @ApiOperation(value = "Create Withdrawal")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "create withdrawal successfully", response = Response.class),
            @ApiResponse(code = 400, message = "invalid parameter", response = Response.class),
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> createWithdrawalTransaction(@Validated @RequestBody TransactionRequest transactionRequest) throws BaseException {
        TransactionDto transactionDto = TransactionMapper.createTransactionDto(transactionRequest, TransactionTypeEnum.WITHDRAWAL);
        withdrawalService.createWithdrawalTransaction(transactionDto);
        return success("create withdrawal successfully", null, HttpStatus.OK);
    }

}
