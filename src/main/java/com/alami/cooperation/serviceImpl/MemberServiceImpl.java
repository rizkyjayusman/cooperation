package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.MemberDto;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.exception.BaseException;
import com.alami.cooperation.mapper.MemberMapper;
import com.alami.cooperation.repository.MemberRepository;
import com.alami.cooperation.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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
        Member savedMember = memberRepository.save(MemberMapper.createMember(memberDto));
        return MemberMapper.createMemberDto(savedMember);
    }

    @Override
    public Member getMemberById(Long memberId) throws BaseException {
        Optional<Member> member = memberRepository.findById(memberId);
        if(! member.isPresent()) {
            throw new BaseException("member not found");
        }

        return member.get();
    }
}
