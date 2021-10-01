package com.alami.cooperation.repository;

import com.alami.cooperation.entity.Loan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Long> {

    Loan getByMemberId(Long memberId);

    @Query("select sum(l.amount) as total_loan from loans l")
    BigDecimal getTotalLoan();
}
