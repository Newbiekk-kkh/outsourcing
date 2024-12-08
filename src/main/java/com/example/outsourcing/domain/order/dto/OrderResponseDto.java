package com.example.outsourcing.domain.order.dto;

import com.example.outsourcing.domain.order.entity.Order;
import com.example.outsourcing.global.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class OrderResponseDto {
    private Long orderId;
    private Long menuId;
    private OrderStatus status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rejectReason;

    public OrderResponseDto(Long orderId, Long menuId, OrderStatus status) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.status = status;
    }

    public OrderResponseDto(Long orderId, Long menuId, OrderStatus status, String rejectReason) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.status = status;
        this.rejectReason = rejectReason;
    }

    public static OrderResponseDto toDto(Order order) {
        if (order.getStatus().equals(OrderStatus.REJECTED)) {
            return new OrderResponseDto(order.getId(), order.getMenu().getId(), order.getStatus(), order.getRejectReason());
        } else {
            return new OrderResponseDto(order.getId(), order.getMenu().getId(), order.getStatus());
        }
    }
}