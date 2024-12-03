package com.example.outsourcing.orders.dto;

import com.example.outsourcing.entity.Orders;
import com.example.outsourcing.eunm.OrdersStatus;
import lombok.Getter;

@Getter
public class OrdersResponseDto {
    private Long orderId;
    private Long menuId;
    private OrdersStatus status;

    public OrdersResponseDto(Long orderId, Long menuId, OrdersStatus status) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.status = status;
    }

    public static OrdersResponseDto toDto(Orders orders) {
        return new OrdersResponseDto(orders.getId(), orders.getMenu().getId(), orders.getStatus());
    }
}
