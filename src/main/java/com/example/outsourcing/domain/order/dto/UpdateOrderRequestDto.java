package com.example.outsourcing.domain.order.dto;

import com.example.outsourcing.global.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderRequestDto {
    private OrderStatus status;
    private String rejectReason;

    public UpdateOrderRequestDto() {
    }

    public UpdateOrderRequestDto(OrderStatus status) {
        this.status = status;
    }

    public UpdateOrderRequestDto(String rejectReason, OrderStatus status) {
        this.rejectReason = rejectReason;
        this.status = status;
    }
}
