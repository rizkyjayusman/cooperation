package com.alami.cooperation.repository;

import com.alami.cooperation.entity.Saving;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface SavingRepository extends JpaRepository<Saving, Long> {

    BigDecimal getTotalSaving();

    Saving getByMemberId(Long memberId);
}
