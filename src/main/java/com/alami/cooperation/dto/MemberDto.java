package com.alami.cooperation.dto;

import com.alami.cooperation.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter @Setter
public class MemberDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String address;

    private Date createdDate;

    public static MemberDto toDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberDto.getId());
        memberDto.setFirstName(member.getFirstName());
        memberDto.setLastName(member.getLastName());
        memberDto.setBirthDate(member.getBirthDate());
        memberDto.setAddress(member.getAddress());
        memberDto.setCreatedDate(member.getCreatedDate());
        return memberDto;
    }
}
