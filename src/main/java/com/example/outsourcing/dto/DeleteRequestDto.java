package com.example.outsourcing.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteRequestDto {
    private final Long id;
    private final String password;
}
