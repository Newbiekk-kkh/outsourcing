package com.example.outsourcing.orders.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class OrdersRequestDto {
    private Long menuId;

    public OrdersRequestDto(Long menuId) {
        this.menuId = menuId;
    }
}
