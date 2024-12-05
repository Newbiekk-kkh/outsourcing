package com.example.outsourcing.Store.repository;

import com.example.outsourcing.entity.Member;
import com.example.outsourcing.Store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    default Store findByIdOrElseThrow(Long memberId) {
        return findById(memberId).orElseThrow(() -> new IllegalArgumentException("조회할 수 없는 가게 정보입니다"));
    }

    List<Store> findByNameContaining(String name);
}
