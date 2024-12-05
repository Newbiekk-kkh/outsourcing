package com.example.outsourcing.Store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

@Slf4j
@Getter
public class StoreRequestDto {

    @NotBlank
    String name;
    @NotBlank
    LocalTime openTime;
    @NotBlank
    LocalTime closeTime;
    @NotBlank
    int defaultPrice;

    @Builder
    public StoreRequestDto(String name, String openTime, String closeTime, int defaultPrice) {
        this.name = name;
        log.info("-------------{}---------{}", closeTime, openTime);
        this.openTime = LocalTime.parse(openTime);
        this.closeTime = LocalTime.parse(closeTime);
        log.info("-------------{}---------{}", this.closeTime, this.openTime);
        this.defaultPrice = defaultPrice;
    }


}
