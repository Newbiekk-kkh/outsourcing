package com.example.outsourcing.domain.orders.dto;

import lombok.Getter;

@Getter
public class OrdersCountByMemberDto {
    private Long memberId;
    private Long count;

    public OrdersCountByMemberDto(Long memberId, Long count) {
        this.memberId = memberId;
        this.count = count;
    }
}
