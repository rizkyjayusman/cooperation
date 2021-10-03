package com.alami.cooperation.controller;

import com.alami.cooperation.controller.filter.PaginationFilter;
import com.alami.cooperation.controller.request.MemberRequest;
import com.alami.cooperation.dto.MemberDto;
import com.alami.cooperation.entity.Member;
import com.alami.cooperation.service.MemberService;
import com.alami.cooperation.vo.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.alami.cooperation.handler.ResponseHandler.success;

@RestController
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<Object> getMemberList(PaginationFilter paginationFilter) {
        PageRequest pageable = PageRequest.of(paginationFilter.getPage(), paginationFilter.getSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<Member> page = memberService.getMemberList(pageable);
        Pagination pagination = new Pagination(page.getContent(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createMember(@Validated @RequestBody MemberRequest memberRequest) {
        MemberDto memberDto = new MemberDto();
        memberDto.setFirstName(memberRequest.getFirstName());
        memberDto.setLastName(memberRequest.getLastName());
        memberDto.setBirthDate(memberRequest.getBirthDate());
        memberDto.setAddress(memberRequest.getAddress());
        memberService.createMember(memberDto);

        return success("create member successfully", null, HttpStatus.NO_CONTENT);
    }

}
