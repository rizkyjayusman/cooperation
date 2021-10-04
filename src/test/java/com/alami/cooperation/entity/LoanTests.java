package com.alami.cooperation.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

public class LoanTests {

    @Test
    public void createLoan_shouldReturnLoan_givenValidLoan() {
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setMemberId(1L);
        loan.setAmount(new BigDecimal(1000));
        Date date = new Date();
        loan.setCreatedDate(date);
        loan.setUpdatedDate(date);

        Assertions.assertNotNull(loan);
        Assertions.assertEquals(1L, loan.getId());
        Assertions.assertEquals(1L, loan.getMemberId());
        Assertions.assertEquals(new BigDecimal(1000), loan.getAmount());
        Assertions.assertEquals(date, loan.getCreatedDate());
        Assertions.assertEquals(date, loan.getUpdatedDate());
    }

}
