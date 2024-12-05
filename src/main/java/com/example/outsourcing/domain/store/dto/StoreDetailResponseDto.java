package com.example.outsourcing.domain.store.dto;

import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.domain.menu.entity.Menu;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
public class StoreDetailResponseDto {
    private String name;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int defaultPrice;
    private List<Menu> menuList;


    public StoreDetailResponseDto(Store store) {
        this.name = store.getName();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.defaultPrice = store.getPrice();
        this.menuList = store.getMenus();
    }



}
