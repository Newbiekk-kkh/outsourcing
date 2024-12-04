package com.example.outsourcing.entity;

import com.example.outsourcing.eunm.MenuStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "menu")
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private MenuStatus menuStatus;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    public Menu() {
    }

    public Menu(String name, String description, long price, MenuStatus menuStatus,Store store) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.menuStatus = menuStatus;
        this.store = store;
    }

    public void updateMenu(String name, String description, long price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void deleteMenu() {
        this.menuStatus = MenuStatus.DELETED;
    }
}
