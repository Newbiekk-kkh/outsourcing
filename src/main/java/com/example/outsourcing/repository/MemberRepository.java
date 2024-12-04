package com.example.outsourcing.repository;

import com.example.outsourcing.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //이메일 중복 여부 확인
    boolean existsByEmail(@Param("email") String email);

    Optional<Member> findByEmail(@Param("email") String email);

    default Member findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()->new IllegalArgumentException("찾을 수 없는 멤버입니다"));
    }
}
