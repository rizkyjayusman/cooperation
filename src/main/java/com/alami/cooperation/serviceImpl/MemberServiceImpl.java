package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.MemberDto;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.repository.MemberRepository;
import com.alami.cooperation.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Page<Member> getMemberList(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Override
    public void createMember(MemberDto memberDto) {
        Member member = new Member();
        member.setFirstName(memberDto.getFirstName());
        member.setLastName(memberDto.getFirstName());
        member.setBirthDate(memberDto.getBirthDate());
        member.setAddress(memberDto.getAddress());
        memberRepository.save(member);
    }
}
