package com.example.outsourcing.repository;

import com.example.outsourcing.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //이메일 중복 여부 확인
    boolean existsByEmail(@Param("email") String email);

    Optional<Member> findByEmail(@Param("email") String email);

    default Member findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID가 존재하지 않습니다."));
    }
}
