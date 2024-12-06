package com.example.outsourcing.domain.order.dto;

import lombok.Getter;

@Getter
public class OrderRequestDto {
    private Long menuId;

    public OrderRequestDto(Long menuId) {
        this.menuId = menuId;
    }
}
