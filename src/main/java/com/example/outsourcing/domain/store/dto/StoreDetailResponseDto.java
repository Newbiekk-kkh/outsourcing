package com.example.outsourcing.domain.store.dto;

import com.example.outsourcing.domain.menu.dto.GetMenuResponseDto;
import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.domain.menu.entity.Menu;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
public class StoreDetailResponseDto {
    private String name;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int defaultPrice;
    private List<GetMenuResponseDto> menus;


    public static StoreDetailResponseDto of(Store store, List<GetMenuResponseDto> menus) {
        return StoreDetailResponseDto.builder()
                .name(store.getName())
                .defaultPrice(store.getPrice())
                .openTime(store.getOpenTime())
                .closeTime(store.getCloseTime())
                .menus(menus)
                .build();
    }



}
