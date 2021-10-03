package com.alami.cooperation.repository;

import com.alami.cooperation.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long>, JpaRepository<Member, Long> {
}
