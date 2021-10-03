package com.alami.cooperation.repository;

import com.alami.cooperation.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

    @Query("select sum(s.balance) as total_deposit from deposits s")
    BigDecimal getTotalBalance();

    Deposit getByMemberId(Long memberId);

}
