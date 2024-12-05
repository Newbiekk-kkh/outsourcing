package com.example.outsourcing.orders.entity;

import com.example.outsourcing.common.BaseEntity;
import com.example.outsourcing.menu.entity.Menu;
import com.example.outsourcing.Store.entity.Store;
import com.example.outsourcing.eunm.OrdersStatus;
import com.example.outsourcing.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "orders")
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrdersStatus status;

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

    public Orders() {
    }

    public Orders(OrdersStatus status) {
        this.status = status;
    }

    @Builder
    public Orders(OrdersStatus status, Member member, Store store, Menu menu) {
        this.status = status;
        this.member = member;
        this.store = store;
        this.menu = menu;
    }

    public void updateStatus(OrdersStatus status, String rejectReason) {
        this.status = status;
        this.rejectReason = rejectReason;
    }
}
