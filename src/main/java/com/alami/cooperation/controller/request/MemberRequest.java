package com.alami.cooperation.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
public class MemberRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private Date birthDate;

    @NotEmpty
    private String address;

}
