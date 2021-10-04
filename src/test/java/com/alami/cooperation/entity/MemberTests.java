package com.alami.cooperation.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberTests {

    @Test
    public void createMember_shouldReturnMember_givenValidMember() throws ParseException {
        Member member = new Member();
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
