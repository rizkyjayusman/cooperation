package com.alami.cooperation.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
public class TransactionRequest {

    @NotNull
    private Date transactionDate;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long memberId;

}
