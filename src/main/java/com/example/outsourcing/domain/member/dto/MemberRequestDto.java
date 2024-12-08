package com.example.outsourcing.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberRequestDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String access;

    public void setPassword(String password) {
        this.password = password;
    }
}
