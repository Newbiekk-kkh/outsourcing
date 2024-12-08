package com.example.outsourcing.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class StoreDeleteDto {

    @NotBlank
    public String email;

    public StoreDeleteDto(String email) {
        this.email = email;
    }
}
