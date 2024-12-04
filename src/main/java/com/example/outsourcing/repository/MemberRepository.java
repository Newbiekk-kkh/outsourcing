package com.example.outsourcing.repository;

import com.example.outsourcing.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //이메일 중복 여부 확인
    boolean existsByEmail(@Param("email") String email);

    Optional<Member> findByEmail(@Param("email") String email);


    default Member findByIdOrElseThrow(Long memberId) {
        return findById(memberId).orElseThrow(() -> new IllegalArgumentException("조회할 수 없는 사용자입니다"));
    }

}
