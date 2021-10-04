package com.alami.cooperation.controller.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class TransactionFilter {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fromDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    private Long memberId;

}
