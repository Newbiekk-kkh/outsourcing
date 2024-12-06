package com.example.outsourcing.domain.menu.dto;

import com.example.outsourcing.domain.menu.entity.MenuOption;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteMenuOptionResponseDto {
    private String menuOptionName;

    public static DeleteMenuOptionResponseDto deleteMenuOptionResponse(MenuOption menuOption) {
        return DeleteMenuOptionResponseDto.builder()
                .menuOptionName(menuOption.getMenuOptionName())
                .build();
    }
}
