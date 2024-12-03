package com.example.outsourcing.orders.dto;

import com.example.outsourcing.entity.Orders;
import com.example.outsourcing.eunm.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class OrdersResponseDto {
    private Long orderId;
    private Long menuId;
    private OrderStatus status;

    public OrdersResponseDto(Long orderId, Long menuId, OrderStatus status) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.status = status;
    }

    public static OrdersResponseDto toDto(Orders orders) {
        return new OrdersResponseDto(orders.getId(), orders.getMenu().getId(), orders.getStatus());
    }
}
