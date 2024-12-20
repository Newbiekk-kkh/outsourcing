package com.example.outsourcing.domain.member.entity;

import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.global.common.BaseEntity;
import com.example.outsourcing.global.enums.UserAccess;
import com.example.outsourcing.global.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserAccess userAccess;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus userStatus;


    @OneToMany(mappedBy = "member")
    List<Store> stores = new ArrayList<>();

    public Member(String email, String password, UserAccess userAccess, UserStatus userStatus) {
        this.email = email;
        this.password = password;
        this.userAccess = userAccess;
        this.userStatus = userStatus;
    }


    public void delete(UserStatus status) {
        this.userStatus = status;
    }


}
