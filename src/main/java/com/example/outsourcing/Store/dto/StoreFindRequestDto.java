package com.example.outsourcing.Store.dto;

import lombok.Getter;
import org.springframework.web.service.annotation.GetExchange;

@Getter
public class StoreFindRequestDto {

    String name;

    public StoreFindRequestDto(String name) {
        this.name = name;
    }
}
