package com.example.outsourcing.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrdersErrorCode {
    FAIL_TO_MEET_MIN_PRICE(HttpStatus.BAD_REQUEST, "최소 주문 금액을 채워주세요."),
    NOT_BUSINESS_HOURS(HttpStatus.BAD_REQUEST, "가게 영업 시간이 아닙니다."),
    ALREADY_REJECTED(HttpStatus.BAD_REQUEST, "이미 거절된 요청입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
