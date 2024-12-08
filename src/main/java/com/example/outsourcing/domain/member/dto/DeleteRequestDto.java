package com.example.outsourcing.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteRequestDto {
    @NotNull
    private final Long id;
    @NotBlank
    private final String password;
}
