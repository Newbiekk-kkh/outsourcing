package com.example.outsourcing.domain.menu.entity;

import com.example.outsourcing.global.enums.MenuOptionStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "menu_option")
public class MenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String menuOptionName;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private MenuOptionStatus menuOptionStatus;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    public MenuOption() {}

    public MenuOption(String menuOptionName, long price, MenuOptionStatus menuOptionStatus, Menu menu) {
        this.menuOptionName = menuOptionName;
        this.price = price;
        this.menuOptionStatus = menuOptionStatus;
        this.menu = menu;
    }

    public void UpdateMenuOption(String menuOptionName, long price) {
        this.menuOptionName = menuOptionName;
        this.price = price;
    }

    public void ChangeMenuOptionStatus(MenuOptionStatus menuOptionStatus) {
        this.menuOptionStatus = menuOptionStatus;
    }
}
