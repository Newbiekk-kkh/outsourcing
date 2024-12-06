package com.example.outsourcing.domain.menu.dto;

import com.example.outsourcing.domain.menu.entity.MenuOption;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMenuOptionResponseDto {
    private String menuOptionName;

    public static CreateMenuOptionResponseDto createMenuOptionResponse(MenuOption menuOption) {
        return CreateMenuOptionResponseDto.builder()
                .menuOptionName(menuOption.getMenuOptionName())
                .build();
    }
}
