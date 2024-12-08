package com.example.outsourcing.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderException extends Exception {
    private final OrderErrorCode errorCode;
}
