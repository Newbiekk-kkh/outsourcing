package com.example.outsourcing.entity;

import com.example.outsourcing.eunm.StoreStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreStatus status;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Time openTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Time closeTime;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menu = new ArrayList<>();

    public Store() {
    }

    public Store(long price, StoreStatus status) {
        this.price = price;
        this.status = status;
    }
}
