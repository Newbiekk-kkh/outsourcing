package com.example.outsourcing.dto;

import com.example.outsourcing.entity.Menu;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateMenuResponseDto {
    private String menuName;

    public static UpdateMenuResponseDto updateMenuResponse(Menu menu) {
        return UpdateMenuResponseDto.builder()
                .menuName(menu.getName())
                .build();
    }
}
