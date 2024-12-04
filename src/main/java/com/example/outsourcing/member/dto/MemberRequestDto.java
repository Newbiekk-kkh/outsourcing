package com.example.outsourcing.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberRequestDto {

    private String email;
    private String password;
    private String access;

    public void setPassword(String password) {
        this.password = password;
    }
}
