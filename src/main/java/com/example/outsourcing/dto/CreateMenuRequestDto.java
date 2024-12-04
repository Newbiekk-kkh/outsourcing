package com.example.outsourcing.dto;

import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Store;
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
