package com.example.outsourcing.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteRequestDto {
    @NotBlank
    private final Long id;
    @NotBlank
    private final String password;
}
