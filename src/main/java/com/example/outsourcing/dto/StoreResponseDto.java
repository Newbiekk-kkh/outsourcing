package com.example.outsourcing.dto;

import com.example.outsourcing.entity.Store;
import lombok.Getter;

import javax.imageio.plugins.tiff.TIFFDirectory;
import java.sql.Time;

@Getter
public class StoreResponseDto {

    private final String name;
    private final Time openTime;
    private final Time closeTime;
    private final Long defaultPrice;


    public StoreResponseDto(Store store) {
        this.name = store.getName();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.defaultPrice = store.getPrice();
    }
}
