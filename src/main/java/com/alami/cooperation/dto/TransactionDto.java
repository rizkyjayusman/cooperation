package com.alami.cooperation.dto;

import com.alami.cooperation.enumtype.TransactionTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
public class TransactionDto {

    private Long memberId;

    private BigDecimal amount;

    private TransactionTypeEnum transactionType;

    private Date transactionDate;

}
