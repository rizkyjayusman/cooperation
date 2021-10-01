package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.MemberRequest;
import com.alami.cooperation.dto.MemberDto;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public Page<Member> getMemberList(Pageable pageable) {
        return memberService.getMemberList(pageable);
    }

    @PostMapping
    public void createMember(@Validated @RequestBody MemberRequest memberRequest) {
        MemberDto memberDto = new MemberDto();
        memberDto.setFirstName(memberRequest.getFirstName());
        memberDto.setLastName(memberRequest.getLastName());
        memberDto.setBirthDate(memberRequest.getBirthDate());
        memberDto.setAddress(memberRequest.getAddress());
        memberService.createMember(memberDto);
    }

}
