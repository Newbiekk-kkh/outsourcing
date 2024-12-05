package com.example.outsourcing.review.entity;

import com.example.outsourcing.member.entity.Member;
import com.example.outsourcing.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

//    @ManyToOne
//    @JoinColumn(name = "store_id")
//    private Store store;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

    private Integer star;

    private String review;
}
