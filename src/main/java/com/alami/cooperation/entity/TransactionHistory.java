package com.alami.cooperation.entity;

import com.alami.cooperation.enumtype.TransactionTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "transaction_histories")
@Table(name = "transaction_histories")
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private BigDecimal amount;

    private TransactionTypeEnum transactionType;

    private Date transactionDate;

    private Date createdDate;

}
