package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.MemberRequest;
import com.alami.cooperation.dto.MemberDto;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static com.alami.cooperation.util.ResponseUtil.toJson;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
public class MemberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

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
    public void getMemberList_shouldReturnHttp200_givenValidMemberList() throws Exception {
        PageRequest pageRequest = PageRequest.of(1,10);
        Member[] memberArr = new Member[] {createMemberWawan(), createMemberTeguh(), createMemberJoko()};
        List<Member> memberList = Arrays.asList(memberArr);
        Page<Member> memberPage = new PageImpl<>(memberList, pageRequest, memberList.size());

        given(memberService.getMemberList(pageRequest)).willReturn(memberPage);

        mockMvc.perform(get("/members?page=1&size=10")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

            verify(memberService, times(1)).getMemberList(refEq(pageRequest));
    }

    @Test
    public void createMember_shouldReturnHttp204_givenValidMember() throws Exception {
        MemberDto mockMemberDto = new MemberDto();
        mockMemberDto.setFirstName("Wawan");
        mockMemberDto.setLastName("Setiawan");
        mockMemberDto.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-10"));
        mockMemberDto.setAddress("Kompleks Asia Serasi No 100");
        given(memberService.createMember(mockMemberDto)).willReturn(mockMemberDto);

        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setFirstName("Wawan");
        memberRequest.setLastName("Setiawan");
        memberRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-10"));
        memberRequest.setAddress("Kompleks Asia Serasi No 100");

        mockMvc.perform(post("/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(memberRequest))
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(memberService, times(1)).createMember(refEq(mockMemberDto));
    }
}
