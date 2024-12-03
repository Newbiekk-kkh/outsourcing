package com.example.outsourcing.orders.dto;

import com.example.outsourcing.eunm.OrdersStatus;
import lombok.Getter;

@Getter
public class UpdateOrdersRequestDto {
    private OrdersStatus status;

    public UpdateOrdersRequestDto(OrdersStatus status) {
        this.status = status;
    }
}
