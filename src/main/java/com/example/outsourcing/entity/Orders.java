package com.example.outsourcing.entity;

import com.example.outsourcing.eunm.OrdersStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "orders")
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrdersStatus status;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Setter
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Setter
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    public Orders() {
    }

    public Orders(OrdersStatus status) {
        this.status = status;
    }
}
