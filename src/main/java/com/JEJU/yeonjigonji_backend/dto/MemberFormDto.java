package com.JEJU.yeonjigonji_backend.dto;

import com.JEJU.yeonjigonji_backend.constant.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberFormDto {
    private String name;
    private String password;
    private String email;
    private Integer age;
    private Gender gender;
}

