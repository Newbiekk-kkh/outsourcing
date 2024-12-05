package com.example.outsourcing.response;

import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;

@Getter
public class CommonResponseBody<T> extends HttpEntity<T> {
    private final String message;
    private final T data;
    private final HttpStatusCode statusCode;

    public CommonResponseBody(String message, T data, HttpStatusCode statusCode) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }

    public CommonResponseBody(String message, T data) {
        this(message, data, null);
    }

    public CommonResponseBody(String message) {
        this(message, null);
    }
}