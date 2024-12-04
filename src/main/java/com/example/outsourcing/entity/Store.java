package com.example.outsourcing.entity;

import com.example.outsourcing.eunm.StoreStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;
import java.util.List;

@Getter
@Entity
@Table(name = "store")
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

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

    public Store() {
    }

    @Builder
    public Store(String name, long price, Time openTime, Time closeTime) {
        this.name = name;
        this.price = price;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.status = StoreStatus.NORMAL;
    }

    public void delete(){
        this.status = StoreStatus.DELETED;
    }
}
