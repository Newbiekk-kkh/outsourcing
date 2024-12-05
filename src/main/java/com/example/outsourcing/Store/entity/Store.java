package com.example.outsourcing.Store.entity;

import com.example.outsourcing.Store.dto.StoreRequestDto;
import com.example.outsourcing.eunm.StoreStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Entity
@Table(name = "store")
public class Store {
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
    private LocalTime openTime;

    @Column(nullable = false)
    private LocalTime closeTime;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Store() {
    }

    @Builder
    public Store(String name, LocalTime openTime, LocalTime closeTime, Long price, Member member) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.price = price;
        this.status = StoreStatus.NORMAL;
    }




    public Store(long price, StoreStatus status) {
        this.price = price;
        this.status = status;
    }

    public void update(StoreRequestDto requestDto) {
        this.name = requestDto.getName();
        this.openTime = requestDto.getOpenTIme();
        this.closeTime = requestDto.getCloseTime();
        this.price = requestDto.getDefaultPrice();
    }

    public void delete() {
        this.status = StoreStatus.DELETED;
    }
}
