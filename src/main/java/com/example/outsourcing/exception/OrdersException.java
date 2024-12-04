package com.example.outsourcing.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrdersException extends Exception {
    private final OrdersErrorCode errorCode;
}
