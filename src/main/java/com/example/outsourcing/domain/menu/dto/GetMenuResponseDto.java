package com.example.outsourcing.domain.menu.dto;

import com.example.outsourcing.domain.menu.entity.Menu;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMenuResponseDto {
    private String menuName;
    private String menuDescription;
    private long menuPrice;

    public static GetMenuResponseDto getMenuResponse(Menu menu) {
        return GetMenuResponseDto.builder()
                .menuName(menu.getName())
                .menuDescription(menu.getDescription())
                .menuPrice(menu.getPrice())
                .build();
    }
}
