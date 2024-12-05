package com.example.outsourcing.review.entity;

import com.example.outsourcing.Store.entity.Store;
import com.example.outsourcing.member.entity.Member;
import com.example.outsourcing.common.BaseEntity;
import com.example.outsourcing.orders.entity.Orders;
import com.example.outsourcing.review.dto.ReviewRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    private int star;

    private String review;

}
