package com.example.outsourcing.orders.dto;

import com.example.outsourcing.orders.entity.Orders;
import com.example.outsourcing.eunm.OrdersStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class OrdersResponseDto {
    private Long ordersId;
    private Long menuId;
    private OrdersStatus status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rejectReason;

    public OrdersResponseDto(Long orderId, Long menuId, OrdersStatus status) {
        this.ordersId = orderId;
        this.menuId = menuId;
        this.status = status;
    }

    public OrdersResponseDto(Long orderId, Long menuId, OrdersStatus status, String rejectReason) {
        this.ordersId = orderId;
        this.menuId = menuId;
        this.status = status;
        this.rejectReason = rejectReason;
    }

    public static OrdersResponseDto toDto(Orders orders) {
        if (!orders.getStatus().equals(OrdersStatus.REJECTED)) {
            return new OrdersResponseDto(orders.getId(), orders.getMenu().getId(), orders.getStatus(), orders.getRejectReason());
        } else {
            return new OrdersResponseDto(orders.getId(), orders.getMenu().getId(), orders.getStatus());
        }
    }
}