package com.alami.cooperation.mapper;

import com.alami.cooperation.entity.Deposit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

public class DepositMapperTests {

    @Test
    public void createDeposit_shouldReturnDeposit_givenValidDeposit() {
        Deposit deposit = new Deposit();
        deposit.setMemberId(1L);
        deposit.setBalance(new BigDecimal(1000));
        Date date = new Date();
        deposit.setCreatedDate(date);
        deposit.setUpdatedDate(date);

        Deposit testedDeposit = DepositMapper.createDeposit(deposit.getMemberId(), deposit.getBalance());
        Assertions.assertNotNull(testedDeposit);
        Assertions.assertEquals(deposit.getMemberId(), testedDeposit.getMemberId());
        Assertions.assertEquals(deposit.getBalance(), testedDeposit.getBalance());
    }

    @Test
    public void updateDeposit_shouldUpdateDeposit_givenValidDeposit() {
        Deposit deposit = new Deposit();
        deposit.setMemberId(1L);
        deposit.setBalance(new BigDecimal(0));
        Date date = new Date();
        deposit.setCreatedDate(date);
        deposit.setUpdatedDate(date);

        DepositMapper.updateDeposit(deposit, deposit.getBalance());
        Assertions.assertEquals(deposit.getBalance(), deposit.getBalance());
    }


}




