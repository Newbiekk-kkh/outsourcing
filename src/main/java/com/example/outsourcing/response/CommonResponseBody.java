package com.example.outsourcing.response;

import lombok.Getter;

import java.util.PrimitiveIterator;

@Getter
public class CommonResponseBody<T> {
    private final String message;
    private final T date;

    public CommonResponseBody(String message, T date) {
        this.message = message;
        this.date = date;
    }

    public CommonResponseBody(String message) {
        this(message, null);
    }
}
