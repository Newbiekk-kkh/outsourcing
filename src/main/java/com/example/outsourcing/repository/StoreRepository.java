package com.example.outsourcing.repository;

import com.example.outsourcing.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
