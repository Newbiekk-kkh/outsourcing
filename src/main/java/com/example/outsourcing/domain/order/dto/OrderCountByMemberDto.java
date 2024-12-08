package com.example.outsourcing.domain.order.dto;

import lombok.Getter;

@Getter
public class OrderCountByMemberDto {
    private Long memberId;
    private Long count;

    public OrderCountByMemberDto(Long memberId, Long count) {
        this.memberId = memberId;
        this.count = count;
    }
}
