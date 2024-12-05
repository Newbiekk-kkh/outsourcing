package com.example.outsourcing.Store.dto;

import com.example.outsourcing.Store.entity.Store;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StoreResponseDto {

    private final String name;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final int defaultPrice;


    public StoreResponseDto(Store store) {
        this.name = store.getName();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.defaultPrice = store.getPrice();
    }
}
