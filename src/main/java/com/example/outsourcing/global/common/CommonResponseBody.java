package com.example.outsourcing.global.common;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class CommonResponseBody<T> {
    private final String message;
    private final T data;

    public CommonResponseBody(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public CommonResponseBody(String message) {
        this(message, null);
    }
}