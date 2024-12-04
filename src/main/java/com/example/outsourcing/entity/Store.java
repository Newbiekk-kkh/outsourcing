package com.example.outsourcing.entity;

import com.example.outsourcing.Store.dto.StoreRequestDto;
import com.example.outsourcing.eunm.StoreStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
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

//    @Column(nullable = false)
    private LocalTime openTime;

//    @Column(nullable = false)
    private LocalTime closeTime;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "store")
    private List<Menu> menus = new ArrayList<>();


    public Store() {
    }

    @Builder
    public Store(String name, long price, LocalTime openTime, LocalTime closeTime, Member member) {
        this.name = name;
        this.price = price;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.status = StoreStatus.NORMAL;
        this.member = member;
    }

    public void update(StoreRequestDto dto) {
        this.name = dto.getName();
        this.price = dto.getDefaultPrice();
        this.openTime = dto.getOpenTIme();
        this.closeTime = dto.getCloseTime();
    }

    public void delete(){
        this.status = StoreStatus.DELETED;
    }
}
