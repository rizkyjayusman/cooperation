package com.alami.cooperation.entity;

import com.alami.cooperation.enumtype.TransactionTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "transactions")
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private BigDecimal amount;

    private TransactionTypeEnum transactionType;

    private Date transactionDate;

    private Date createdDate;

}
