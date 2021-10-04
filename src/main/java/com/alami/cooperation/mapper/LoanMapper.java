package com.alami.cooperation.mapper;

import com.alami.cooperation.entity.Loan;

import java.math.BigDecimal;
import java.util.Date;

public class LoanMapper {

    public static Loan createLoan(Long memberId, BigDecimal amount) {
        Loan loan = new Loan();
        loan.setMemberId(memberId);
        loan.setAmount(amount);
        loan.setCreatedDate(new Date());

        return loan;
    }

    public static void updateLoan(Loan loan, BigDecimal amount) {
        loan.setAmount(amount);
        loan.setUpdatedDate(new Date());
    }

}
