package com.example.outsourcing.domain.store.entity;

import com.example.outsourcing.domain.order.entity.Order;
import com.example.outsourcing.domain.store.dto.StoreRequestDto;
import com.example.outsourcing.global.enums.StoreStatus;
import com.example.outsourcing.domain.member.entity.Member;
import com.example.outsourcing.domain.menu.entity.Menu;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
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
    private String name;

    @Column(nullable = false)
    private int price;

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


    @OneToMany(mappedBy = "store")
    private List<Menu> menus = new ArrayList<>();


    @OneToMany(mappedBy = "store")
    private List<Order> orders = new ArrayList<>();

    public Store() {
    }

    @Builder
    public Store(String name, LocalTime openTime, LocalTime closeTime, int price, Member member) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.price = price;
        this.status = StoreStatus.NORMAL;
        this.member = member;
    }




    public Store(int price, StoreStatus status) {
        this.price = price;
        this.status = status;
    }

    public void update(StoreRequestDto requestDto) {
        this.name = requestDto.getName();
        this.openTime = requestDto.getOpenTime();
        this.closeTime = requestDto.getCloseTime();
        this.price = requestDto.getDefaultPrice();
    }

    public void delete() {
        this.status = StoreStatus.DELETED;
    }
}