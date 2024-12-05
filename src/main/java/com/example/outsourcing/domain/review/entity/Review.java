package com.example.outsourcing.domain.review.entity;

import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.domain.member.entity.Member;
import com.example.outsourcing.global.common.BaseEntity;
import com.example.outsourcing.domain.orders.entity.Orders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
