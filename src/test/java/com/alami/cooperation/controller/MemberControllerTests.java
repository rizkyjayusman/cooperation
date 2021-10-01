package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.MemberRequest;
import com.alami.cooperation.dto.MemberDto;
import com.alami.cooperation.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;

import static com.alami.cooperation.util.ResponseUtil.toJson;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
public class MemberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    public void createMember_shouldReturnHttp200_givenValidMember() throws Exception {
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
                .andExpect(status().isOk());

        verify(memberService, times(1)).createMember(refEq(mockMemberDto));
    }
}
