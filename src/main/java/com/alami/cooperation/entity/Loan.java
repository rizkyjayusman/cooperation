package com.alami.cooperation.entity;

import com.alami.cooperation.dto.TransactionDto;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "loans")
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private BigDecimal amount;

    private Date createdDate;

    private Date updatedDate;

    public void subtractAmount(TransactionDto transactionDto) {
        amount = amount.subtract(transactionDto.getAmount());
    }

    public void addAmount(TransactionDto transactionDto) {
        amount = amount.add(transactionDto.getAmount());
    }
}
