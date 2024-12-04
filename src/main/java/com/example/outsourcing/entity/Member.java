package com.example.outsourcing.entity;

import com.example.outsourcing.common.BaseEntity;
import com.example.outsourcing.common.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String userAccess;

    private String userStatus;

    public Member(String email, String password, String userAccess, String userStatus) {
        this.email = email;
        this.password = password;
        this.userAccess = userAccess;
        this.userStatus = userStatus;
    }

    public void updateStatus(UserStatus status) {
        this.userStatus = status.getUserStatus();
    }
}
