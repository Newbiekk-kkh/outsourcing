package com.example.outsourcing.menu.dto;

import com.example.outsourcing.entity.Menu;
import lombok.Getter;

@Getter
public class UpdateMenuRequestDto {
    private String menuName;
    private String menuDescription;
    private long menuPrice;
}
