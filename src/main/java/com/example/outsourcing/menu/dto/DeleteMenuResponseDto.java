package com.example.outsourcing.menu.dto;

import com.example.outsourcing.entity.Menu;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteMenuResponseDto {
    private String menuName;

    public static DeleteMenuResponseDto deleteMenuResponse(Menu menu) {
        return DeleteMenuResponseDto.builder()
                .menuName(menu.getName())
                .build();
    }
}
