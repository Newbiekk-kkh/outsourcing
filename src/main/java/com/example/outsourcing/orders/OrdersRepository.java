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
    Optional<Orders> findByStoreId(Long storeId);
    List<Orders> findAllByStoreId(Long storeId);

    default Orders findByStoreIdOrElseThrow(Long storeId) {
        return findByStoreId(storeId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "가게를 찾을 수 없습니다."));
    }
}
