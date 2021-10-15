package com.alami.cooperation.entity;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.mapper.DepositMapper;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "deposits")
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private BigDecimal balance;

    private Date createdDate;

    private Date updatedDate;

    public void addBalance(TransactionDto transactionDto) {
        balance = balance.add(transactionDto.getAmount());
    }

    public void subtractBalance(TransactionDto transactionDto) {
        balance = balance.subtract(transactionDto.getAmount());
    }
}
