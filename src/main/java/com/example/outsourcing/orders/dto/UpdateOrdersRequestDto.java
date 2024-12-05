package com.example.outsourcing.orders.dto;

import com.example.outsourcing.eunm.OrdersStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrdersRequestDto {
    private OrdersStatus status;
    private String rejectReason;

    public UpdateOrdersRequestDto() {
    }

    public UpdateOrdersRequestDto(OrdersStatus status) {
        this.status = status;
    }

    public UpdateOrdersRequestDto(String rejectReason, OrdersStatus status) {
        this.rejectReason = rejectReason;
        this.status = status;
    }
}
