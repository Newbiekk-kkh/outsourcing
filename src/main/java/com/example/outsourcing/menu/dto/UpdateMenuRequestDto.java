package com.example.outsourcing.menu.dto;

import lombok.Getter;

@Getter
public class UpdateMenuRequestDto {
    private String menuName;
    private String menuDescription;
    private long menuPrice;
}
