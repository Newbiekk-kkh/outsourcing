package com.example.outsourcing.entity;

import com.example.outsourcing.eunm.MemberAccess;
import com.example.outsourcing.eunm.MemberStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberAccess access;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status;

    public Member() {
    }

    public Member(String email, String password, MemberAccess access) {
        this.email = email;
        this.password = password;
        this.access = access;
    }
}
