package com.example.outsourcing.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
        if (isValidTimeFormat(openTime) || isValidTimeFormat(closeTime)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "오픈시간과 마감시간을 형식에 맞게 입력해주세요.");
        }
        this.openTime = LocalTime.parse(openTime);
        this.closeTime = LocalTime.parse(closeTime);
        log.info("-------------{}---------{}", this.closeTime, this.openTime);
        this.defaultPrice = defaultPrice;
    }



    public static boolean isValidTimeFormat(String time) {
        // 정규식: HH:mm:ss 형식 확인 (00~23, 00~59, 00~59)
        String timePattern = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";
        return time == null || !time.matches(timePattern);
    }

}
