package com.example.outsourcing.domain.menu.dto;

import com.example.outsourcing.domain.menu.entity.Menu;
import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.global.enums.MenuStatus;
import lombok.Getter;

@Getter
public class CreateMenuRequestDto {
    private String menuName;
    private String menuDescription;
    private long menuPrice;

    public Menu toEntity(Store store) {
        return new Menu(
                this.menuName
                ,this.menuDescription
                ,this.menuPrice
                ,MenuStatus.NORMAL
                ,store);
    }
}
