package com.example.outsourcing.Store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StoreRequestDto {

    @NotBlank
    String name;
    @NotBlank
    LocalTime openTIme;
    @NotBlank
    LocalTime closeTime;
    @NotBlank
    Long defaultPrice;

    @Builder
    public StoreRequestDto(String name, LocalTime openTIme, LocalTime closeTime, Long defaultPrice) {
        this.name = name;
        this.openTIme = openTIme;
        this.closeTime = closeTime;
        this.defaultPrice = defaultPrice;
    }


}
