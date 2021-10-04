package com.alami.cooperation.mapper;

import com.alami.cooperation.dto.MemberDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberMapperTests {

    @Test
    public void createMember_shouldReturnMember_givenValidMember() throws ParseException {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(1L);
        memberDto.setFirstName("Wawan");
        memberDto.setLastName("Setiawan");
        memberDto.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-10"));
        memberDto.setAddress("Kompleks Asia Serasi No 100");
        memberDto.setCreatedDate(new Date());

        Member testedMember = MemberMapper.createMember(memberDto);
        Assertions.assertNotNull(testedMember);
        Assertions.assertEquals(memberDto.getFirstName(), testedMember.getFirstName());
        Assertions.assertEquals(memberDto.getLastName(), testedMember.getLastName());
        Assertions.assertEquals(memberDto.getBirthDate(), testedMember.getBirthDate());
        Assertions.assertEquals(memberDto.getAddress(), testedMember.getAddress());
    }


    @Test
    public void createMemberDto_shouldReturnMemberDto_givenValidMemberDto() throws ParseException {
        Member member = new Member();
        member.setId(1L);
        member.setFirstName("Wawan");
        member.setLastName("Setiawan");
        member.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-10"));
        member.setAddress("Kompleks Asia Serasi No 100");
        member.setCreatedDate(new Date());

        MemberDto testedMemberDto = MemberMapper.createMemberDto(member);
        Assertions.assertNotNull(testedMemberDto);
        Assertions.assertEquals(member.getId(), testedMemberDto.getId());
        Assertions.assertEquals(member.getFirstName(), testedMemberDto.getFirstName());
        Assertions.assertEquals(member.getLastName(), testedMemberDto.getLastName());
        Assertions.assertEquals(member.getBirthDate(), testedMemberDto.getBirthDate());
        Assertions.assertEquals(member.getAddress(), testedMemberDto.getAddress());
        Assertions.assertEquals(member.getCreatedDate(), testedMemberDto.getCreatedDate());
    }
}




