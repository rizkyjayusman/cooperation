package com.alami.cooperation.repository;

import com.alami.cooperation.entity.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface SavingRepository extends JpaRepository<Saving, Long> {

    @Query("select sum(s.amount) as total_saving from savings s")
    BigDecimal getTotalSaving();

    Saving getByMemberId(Long memberId);
}
