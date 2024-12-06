package com.example.outsourcing.domain.order.repository;

import com.example.outsourcing.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import com.example.outsourcing.domain.order.dto.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // 가게Id로 주문 조회하기
    Optional<Order> findByStoreId(Long storeId);

    // 가게Id로 전체 주문 조회하기
    List<Order> findAllByStoreId(Long storeId);

    // 주문한 고객 아이디로 주문횟수 확인
    @Query("select new com.example.outsourcing.domain.order.dto.OrderCountByMemberDto(m.id, count(o)) " +
            " from Order o join o.member m where m.id = :memberId")
    OrderCountByMemberDto countOrdersByMember(@Param("memberId") Long memberId);

    // 가게Id로 주문 조회하기
    default Order findByStoreIdOrElseThrow(Long storeId) {
        return findByStoreId(storeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "가게를 찾을 수 없습니다."));
    }

    // 주문Id로 주문 조회하기
    default Order findByIdOrElseThrow(Long ordersId) {
        return findById(ordersId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."));
    }
}
