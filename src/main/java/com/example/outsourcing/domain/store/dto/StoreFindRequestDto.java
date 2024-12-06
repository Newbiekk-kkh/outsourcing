package com.example.outsourcing.domain.store.dto;

import lombok.Getter;

@Getter
public class StoreFindRequestDto {

    String name;

    public StoreFindRequestDto(String name) {
        this.name = name;
    }
}
