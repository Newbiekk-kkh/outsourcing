package com.example.outsourcing.domain.order.entity;

import com.example.outsourcing.global.common.BaseEntity;
import com.example.outsourcing.domain.menu.entity.Menu;
import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.global.enums.OrderStatus;
import com.example.outsourcing.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = true)
    private String rejectReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    public Order() {
    }

    public Order(OrderStatus status) {
        this.status = status;
    }

    @Builder
    public Order(OrderStatus status, Member member, Store store, Menu menu) {
        this.status = status;
        this.member = member;
        this.store = store;
        this.menu = menu;
    }

    public void updateStatus(OrderStatus status, String rejectReason) {
        this.status = status;
        this.rejectReason = rejectReason;
    }
}
