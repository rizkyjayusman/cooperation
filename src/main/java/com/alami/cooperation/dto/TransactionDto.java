package com.alami.cooperation.dto;

import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
public class TransactionDto {

    @JsonProperty("member_id")
    private Long memberId;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("transaction_type")
    private TransactionTypeEnum transactionType;

    @JsonProperty("transaction_date")
    private Date transactionDate;

}
