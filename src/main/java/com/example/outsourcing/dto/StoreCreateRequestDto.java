package com.example.outsourcing.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;

@Getter
public class StoreCreateRequestDto {

    @NotBlank
    String name;
    @NotBlank
    Time openTIme;
    @NotBlank
    Time closeTime;
    @NotBlank
    Long defaultPrice;

    @Builder
    public StoreCreateRequestDto(String name, Time openTIme, Time closeTime, Long defaultPrice) {
        this.name = name;
        this.openTIme = openTIme;
        this.closeTime = closeTime;
        this.defaultPrice = defaultPrice;
    }


}
