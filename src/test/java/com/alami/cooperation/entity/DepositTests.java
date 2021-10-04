package com.alami.cooperation.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

public class DepositTests {

    @Test
    public void createDeposit_shouldReturnDeposit_givenValidDeposit() {
        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setMemberId(1L);
        deposit.setBalance(new BigDecimal(1000));
        Date date = new Date();
        deposit.setCreatedDate(date);
        deposit.setUpdatedDate(date);

        Assertions.assertNotNull(deposit);
        Assertions.assertEquals(1L, deposit.getId());
        Assertions.assertEquals(1L, deposit.getMemberId());
        Assertions.assertEquals(new BigDecimal(1000), deposit.getBalance());
        Assertions.assertEquals(date, deposit.getCreatedDate());
        Assertions.assertEquals(date, deposit.getUpdatedDate());
    }

}
