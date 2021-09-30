package com.alami.cooperation.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
public class TransactionRequest {

    private Date transactionDate;

    private BigDecimal amount;

}
