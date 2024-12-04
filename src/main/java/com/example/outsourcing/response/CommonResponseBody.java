package com.example.outsourcing.response;

import lombok.Getter;

import java.util.PrimitiveIterator;

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
