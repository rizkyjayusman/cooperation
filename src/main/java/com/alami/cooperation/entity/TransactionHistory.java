package com.alami.cooperation.entity;

import com.alami.cooperation.enumtype.TransactionTypeEnum;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Document(collection = "transaction_histories")
public class TransactionHistory {

    @Id
    private String id;

    private Long memberId;

    private BigDecimal amount;

    private TransactionTypeEnum transactionType;

    private Date transactionDate;

    private Date createdDate;

}
