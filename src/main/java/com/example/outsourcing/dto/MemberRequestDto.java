package com.example.outsourcing.dto;

import com.example.outsourcing.entity.Member;
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
