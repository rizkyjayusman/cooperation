package com.alami.cooperation.mapper;

import com.alami.cooperation.dto.MemberDto;
import com.alami.cooperation.entity.Member;

import java.util.Date;

public class MemberMapper {

    public static Member createMember(MemberDto memberDto) {
        Member member = new Member();
        member.setFirstName(memberDto.getFirstName());
        member.setLastName(memberDto.getLastName());
        member.setBirthDate(memberDto.getBirthDate());
        member.setAddress(memberDto.getAddress());
        member.setCreatedDate(new Date());

        return member;
    }

    public static MemberDto createMemberDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setFirstName(member.getFirstName());
        memberDto.setLastName(member.getLastName());
        memberDto.setBirthDate(member.getBirthDate());
        memberDto.setAddress(member.getAddress());
        memberDto.setCreatedDate(member.getCreatedDate());
        return memberDto;
    }

}
