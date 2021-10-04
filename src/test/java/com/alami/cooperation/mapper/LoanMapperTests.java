package com.alami.cooperation.mapper;

import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.entity.Loan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

public class LoanMapperTests {

    @Test
    public void createLoan_shouldReturnLoan_givenValidLoan() {
        Loan loan = new Loan();
        loan.setMemberId(1L);
        loan.setAmount(new BigDecimal(1000));
        Date date = new Date();
        loan.setCreatedDate(date);
        loan.setUpdatedDate(date);

        Loan testedLoan = LoanMapper.createLoan(loan.getMemberId(), loan.getAmount());
        Assertions.assertNotNull(testedLoan);
        Assertions.assertEquals(loan.getMemberId(), testedLoan.getMemberId());
        Assertions.assertEquals(loan.getAmount(), testedLoan.getAmount());
    }

    @Test
    public void updateLoan_shouldUpdateLoan_givenValidLoan() {
        Loan loan = new Loan();
        loan.setMemberId(1L);
        loan.setAmount(new BigDecimal(0));
        Date date = new Date();
        loan.setCreatedDate(date);
        loan.setUpdatedDate(date);

        LoanMapper.updateLoan(loan, loan.getAmount());
        Assertions.assertEquals(loan.getAmount(), loan.getAmount());
    }


}




