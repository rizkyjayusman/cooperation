package com.alami.cooperation.service;

import com.alami.cooperation.dto.MemberDto;
import com.alami.cooperation.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    Page<Member> getMemberList(Pageable pageable);

    MemberDto createMember(MemberDto memberDto);

    Member getMemberById(Long memberId);
}
