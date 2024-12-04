package com.example.outsourcing.dto;

import lombok.Getter;

@Getter
public class UpdateMenuRequestDto {
    private String menuName;
    private String menuDescription;
    private long menuPrice;
}
