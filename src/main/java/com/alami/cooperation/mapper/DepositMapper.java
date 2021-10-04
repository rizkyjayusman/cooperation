package com.alami.cooperation.mapper;

import com.alami.cooperation.entity.Deposit;

import java.math.BigDecimal;
import java.util.Date;

public class DepositMapper {

    public static Deposit createDeposit(Long memberId, BigDecimal balance) {
        Deposit deposit = new Deposit();
        deposit.setMemberId(memberId);
        deposit.setCreatedDate(new Date());
        deposit.setBalance(balance);
        return deposit;
    }

    public static void updateDeposit(Deposit deposit, BigDecimal balance) {
        deposit.setBalance(balance);
        deposit.setUpdatedDate(new Date());
    }

}
