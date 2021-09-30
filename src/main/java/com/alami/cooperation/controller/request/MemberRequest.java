package com.alami.cooperation.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class MemberRequest {

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String address;

}
