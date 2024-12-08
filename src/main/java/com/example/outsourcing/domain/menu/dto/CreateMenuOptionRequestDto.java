package com.example.outsourcing.domain.menu.dto;

import com.example.outsourcing.domain.menu.entity.Menu;
import com.example.outsourcing.domain.menu.entity.MenuOption;
import com.example.outsourcing.global.enums.MenuOptionStatus;
import lombok.Getter;

@Getter
public class CreateMenuOptionRequestDto {
    private String menuOptionName;
    private long price;

    public MenuOption toEntity(Menu menu) {
        return new MenuOption(
                this.menuOptionName,
                this.price,
                MenuOptionStatus.UNSELECTED,
                menu
        );
    }
}
