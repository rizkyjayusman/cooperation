package com.alami.cooperation.repository;

import com.alami.cooperation.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

    Page<Transaction> findByMemberId(Long memberId, Pageable pageable);

    Page<Transaction> findByTransactionDateBetween(Date fromDate, Date endDate, Pageable pageable);
}
