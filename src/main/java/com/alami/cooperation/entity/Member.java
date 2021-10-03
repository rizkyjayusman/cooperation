package com.alami.cooperation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "members")
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String address; // TEXT COLUMN DATA TYPE

    private Date createdDate;

}
