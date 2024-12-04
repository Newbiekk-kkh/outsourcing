package com.example.outsourcing.orders;

import com.example.outsourcing.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    // 가게Id로 주문 조회하기
    Optional<Orders> findByStoreId(Long storeId);

    // 가게Id로 전체 주문 조회하기
    List<Orders> findAllByStoreId(Long storeId);

    // 가게Id로 주문 조회하기
    default Orders findByStoreIdOrElseThrow(Long storeId) {
        return findByStoreId(storeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "가게를 찾을 수 없습니다."));
    }

    // 주문Id로 주문 조회하기
    default Orders findByIdOrElseThrow(Long ordersId) {
        return findById(ordersId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."));
    }
}
