package com.example.outsourcing.review.dto;

import com.example.outsourcing.Store.entity.Store;
import com.example.outsourcing.member.entity.Member;
import com.example.outsourcing.orders.entity.Orders;
import com.example.outsourcing.review.entity.Review;
import lombok.Getter;

@Getter
public class ReviewRequestDto {
    private String review;
    private int star;

    public  Review toEntity(Member member, Store store, Orders orders) {
        return Review.builder()
                .member(member)
                .store(store)
                .order(orders)
                .review(this.review)
                .star(this.star)
                .build();
    }
}
