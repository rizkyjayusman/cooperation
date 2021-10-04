package com.alami.cooperation.dto;

import com.alami.cooperation.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberDtoTests {

    @Test
    public void createMemberDto_shouldReturnMemberDto_givenValidMemberDto() throws ParseException {
        MemberDto member = new MemberDto();
        member.setId(1L);
        member.setFirstName("Wawan");
        member.setLastName("Setiawan");
        Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-10");
        member.setBirthDate(birthDate);
        member.setAddress("Kompleks Asia Serasi No 100");
        Date date = new Date();
        member.setCreatedDate(date);

        Assertions.assertNotNull(member);
        Assertions.assertEquals(1L, member.getId());
        Assertions.assertEquals("Wawan", member.getFirstName());
        Assertions.assertEquals("Setiawan", member.getLastName());
        Assertions.assertEquals(birthDate, member.getBirthDate());
        Assertions.assertEquals("Kompleks Asia Serasi No 100", member.getAddress());
        Assertions.assertEquals(date, member.getCreatedDate());
    }

}
