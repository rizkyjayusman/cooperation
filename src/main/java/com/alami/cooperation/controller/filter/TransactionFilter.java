package com.alami.cooperation.controller.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class TransactionFilter {

    private Date fromDate;

    private Date endDate;

    private Long memberId;

}
