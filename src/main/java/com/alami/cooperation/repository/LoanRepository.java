package com.alami.cooperation.repository;

import com.alami.cooperation.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Loan getByMemberId(Long memberId);
}
