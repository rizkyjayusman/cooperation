package com.alami.cooperation.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "savings")
@Table(name = "savings")
public class Saving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private BigDecimal amount;

    private Date createdDate;

    private Date updatedDate;

}
