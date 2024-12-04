package com.example.outsourcing.orders.service;

import com.example.outsourcing.member.entity.Member;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Orders;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.eunm.OrdersStatus;
import com.example.outsourcing.exception.OrdersErrorCode;
import com.example.outsourcing.exception.OrdersException;
import com.example.outsourcing.sesstionUtils.SessionUtils;
import com.example.outsourcing.orders.dto.OrdersResponseDto;
import com.example.outsourcing.member.repository.MemberRepository;
import com.example.outsourcing.orders.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalTime;
import java.util.List;

import static com.example.outsourcing.eunm.OrdersStatus.ORDERED;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final MemberRepository memberRepository;
    private final SessionUtils sessionUtils;

    // 주문 생성 로직
    @Transactional
    public OrdersResponseDto createOrders(Long storeId, Long menuId) throws OrdersException {
        // 로그인한 고객을 주문한 사람으로 설정, 현재 기능별 개발중으로 다른 주요 기능들이 개발되지 않아 임시객체로 사용
        Member loggedInUser = memberRepository.findByIdOrElseThrow(sessionUtils.getLoggedInUserId());
        Store store = new Store();
        Menu menu = new Menu();

        // 최소 주문 금액 미달시 예외처리
        if (store.getPrice() > menu.getPrice()) {
           throw new OrdersException(OrdersErrorCode.FAIL_TO_MEET_MIN_PRICE);
        }

        // 가게 영업 시간이 아닐시 예외처리
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isBefore(store.getOpenTime()) || currentTime.isAfter(store.getCloseTime())) {
            throw new OrdersException(OrdersErrorCode.NOT_BUSINESS_HOURS);
        }

        Orders orders = Orders.builder()
                .status(ORDERED)
                .member(loggedInUser)
                .store(store)
                .menu(menu)
                .build();

        ordersRepository.save(orders);

        return OrdersResponseDto.toDto(orders);
    }

    // 특정 가게에 등록된 전체 주문 조회 로직
    public List<OrdersResponseDto> findAllOrders(Long storeId) {
        // 로그인한 유저가 가게의 Owner 일때만 조회 가능, 로그인한 유저로 가게 오너인지 확인하는 코드 필요
//        storeRepository.findByIdOrElseThrow(storeId);
//        if (findStore.getMember().getId() != sessionUtils.getLoggedInUserId()) {
//            throw new 예외처리;
//        }

        List<Orders> allOrders = ordersRepository.findAllByStoreId(storeId);
        return allOrders.stream().map(OrdersResponseDto::toDto).toList();
    }

    // 특정 가게에 등록된 단일 주문 조회 로직
    public OrdersResponseDto findOrders(Long storeId, Long ordersId) {
        // 로그인한 유저가 고객이거나 Owner 일때만 조회가능, 로그인한 유저가 가게 오너이거나 주문한 고객인지 확인하는 코드 필요
//        storeRepository.findByIdOrElseThrow(storeId);
//        if (findStore.getMember().getId() != sessionUtils.getLoggedInUserId()) {
//            throw new 예외처리;
//        }
//
        Orders findOrders = ordersRepository.findByIdOrElseThrow(ordersId);
//        if (findOrders.getStore().getId() != storeId) {
//            throw new 예외처리;
//        }
//        if (findOrders.getMember().getId() != sessionUtils.getLoggedInUserId()) {
//            throw new 예외처리;
//        }
        return OrdersResponseDto.toDto(findOrders);
    }

    // 특정 가게에 등록된 주문상태 변경 로직
    @Transactional
    public OrdersResponseDto updateOrdersStatus(Long storeId, Long ordersId, OrdersStatus status, String rejectReason) throws OrdersException {
        // 로그인한 유저가 Owner 일때만 변경 가능, 로그인한 유저가 가게오너인지 확인하는 코드 필요
//        storeRepository.findByIdOrElseThrow(storeId);
//        if (!findStore.getMember().getId().equals(sessionUtils.getLoggedInUserId())) {
//            throw new 예외처리;
//        }

        Orders findOrders = ordersRepository.findByIdOrElseThrow(ordersId);
//        if (!findOrders.getStore().getId().equals(storeId)) {
//            throw new 예외처리;
//        }
//        if (findOrders.getStatus().equals(REJECTED)) {
//            throw new OrdersException(OrdersErrorCode.ALREADY_REJECTED);
//        }

        findOrders.updateStatus(status, rejectReason);

        ordersRepository.save(findOrders);

        return OrdersResponseDto.toDto(findOrders);
    }
}
