package com.example.outsourcing.menu.dto;

import com.example.outsourcing.menu.entity.Menu;
import com.example.outsourcing.Store.entity.Store;
import com.example.outsourcing.eunm.MenuStatus;
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
                , MenuStatus.NORMAL
                ,store);
    }
}
