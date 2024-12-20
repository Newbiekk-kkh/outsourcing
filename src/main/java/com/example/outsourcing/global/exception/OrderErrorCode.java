package com.example.outsourcing.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderErrorCode {
    FAIL_TO_MEET_MIN_PRICE(HttpStatus.BAD_REQUEST, "최소 주문 금액을 채워주세요."),
    NOT_BUSINESS_HOURS(HttpStatus.BAD_REQUEST, "가게 영업 시간이 아닙니다."),
    ALREADY_REJECTED(HttpStatus.BAD_REQUEST, "이미 거절된 요청입니다."),
    NOT_ALLOWED_ACCESS(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    REJECT_NEED_REASON(HttpStatus.BAD_REQUEST, "주문을 거절할때 사유를 필수적으로 입력해야합니다."),
    IS_NOT_ORDER_OF_STORE(HttpStatus.BAD_REQUEST, "해당 가게의 주문이 아닙니다."),
    IS_NOT_MENU_OF_STORE(HttpStatus.BAD_REQUEST, "해당 가게의 메뉴가 아닙니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
