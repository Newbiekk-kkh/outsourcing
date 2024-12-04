package com.example.outsourcing.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
    EXISTENCE("존재"),
    SECESSION("탈퇴");

    private final String UserStatus;
}
