package com.example.outsourcing.menu.dto;

import com.example.outsourcing.entity.Menu;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMenuResponseDto {
    private String menuName;

    public static CreateMenuResponseDto createMenuResponse(Menu menu) {
        return CreateMenuResponseDto.builder()
                .menuName(menu.getName())
                .build();
    }
}
