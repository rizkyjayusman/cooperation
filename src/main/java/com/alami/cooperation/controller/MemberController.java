package com.alami.cooperation.controller;

import com.alami.cooperation.controller.filter.Pagination;
import com.alami.cooperation.controller.request.MemberRequest;
import com.alami.cooperation.entity.Member;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {

    @GetMapping
    public List<Member> getMemberList(@RequestBody Pagination pagination) {
        // TODO with paging
        return null;
    }

    @PostMapping
    public void createMember(@RequestBody MemberRequest memberRequest) {}

}
