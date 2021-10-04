package com.alami.cooperation.repository;

import com.alami.cooperation.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TransactionHistoryRepository extends MongoRepository<TransactionHistory, Long> {

    Page<TransactionHistory> findByMemberId(Long memberId, Pageable pageable);

    Page<TransactionHistory> findByTransactionDateBetween(Date fromDate, Date endDate, Pageable pageable);
}
