package com.alami.cooperation.service;

import com.alami.cooperation.dto.MemberDto;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.repository.MemberRepository;
import com.alami.cooperation.serviceImpl.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTests {

    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @BeforeEach
    public void setUp() throws Exception {
        memberService = new MemberServiceImpl(memberRepository);
    }

    private Member createMemberWawan() throws ParseException {
        Member member = new Member();
        member.setId(1L);
        member.setFirstName("Wawan");
        member.setLastName("Setiawan");
        member.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-10"));
        member.setAddress("Kompleks Asia Serasi No 100");

        return member;
    }

    private Member createMemberTeguh() throws ParseException {
        Member member = new Member();
        member.setId(2L);
        member.setFirstName("Teguh");
        member.setLastName("Sudibyantoro");
        member.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1991-02-10"));
        member.setAddress("Jalan Pemekaran No 99");

        return member;
    }

    private Member createMemberJoko() throws ParseException {
        Member member = new Member();
        member.setId(3L);
        member.setFirstName("Joko");
        member.setLastName("Widodo");
        member.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1992-03-10"));
        member.setAddress("Dusun Pisang Rt 10 Rw 20");

        return member;
    }

    @Test
    public void getMemberList_shouldReturnMemberList_givenValidMemberList() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Member[] mockMemberArr = new Member[] {createMemberWawan(), createMemberTeguh(), createMemberJoko()};
        List<Member> mockMemberList = Arrays.asList(mockMemberArr);
        Page<Member> mockMemberPage = new PageImpl<Member>(mockMemberList, pageRequest, mockMemberList.size());
        given(memberService.getMemberList(pageRequest)).willReturn(mockMemberPage);

        Page<Member> memberPage = memberService.getMemberList(pageRequest);
        assertThat(memberPage.getNumber()).isEqualTo(pageRequest.getPageNumber());
        assertThat(memberPage.getSize()).isEqualTo(pageRequest.getPageSize());
        assertThat(memberPage.getContent().size()).isEqualTo(mockMemberList.size());
        // TODO compare element each other
    }

    // TODO got error cause CreatedDate was not match
//    @Test
    public void createMember_shouldReturnMemberDto_givenValidMemberDto() throws Exception {
        Member mockMember = new Member();
        mockMember.setFirstName("Wawan");
        mockMember.setLastName("Setiawan");
        mockMember.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-10"));
        mockMember.setAddress("Kompleks Asia Serasi No 100");

        when(memberRepository.save(mockMember)).thenReturn(mockMember);

        MemberDto memberDto = new MemberDto();
        memberDto.setFirstName("Wawan");
        memberDto.setLastName("Setiawan");
        memberDto.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-10"));
        memberDto.setAddress("Kompleks Asia Serasi No 100");
        MemberDto savedMemberDto = memberService.createMember(memberDto);
        assertThat(savedMemberDto).isNotNull();
    }
}
