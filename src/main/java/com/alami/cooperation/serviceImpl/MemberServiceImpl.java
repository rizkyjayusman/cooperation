package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.MemberDto;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.repository.MemberRepository;
import com.alami.cooperation.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Page<Member> getMemberList(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = new Member();
        member.setFirstName(memberDto.getFirstName());
        member.setLastName(memberDto.getLastName());
        member.setBirthDate(memberDto.getBirthDate());
        member.setAddress(memberDto.getAddress());
        member.setCreatedDate(new Date());
        Member savedMember = memberRepository.saveAndFlush(member);
        return MemberDto.toDto(savedMember);
    }
}
