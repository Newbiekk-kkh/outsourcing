package com.example.outsourcing.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAccess {
    CLIENT("유저"),
    MANAGER("사장님");

    private final String UserAccess;
}
