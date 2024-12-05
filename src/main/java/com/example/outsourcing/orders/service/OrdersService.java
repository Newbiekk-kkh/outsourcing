package com.example.outsourcing.orders.service;

import com.example.outsourcing.Store.repository.StoreRepository;
import com.example.outsourcing.member.entity.Member;
import com.example.outsourcing.menu.entity.Menu;
import com.example.outsourcing.menu.repository.MenuRepository;
import com.example.outsourcing.orders.entity.Orders;
import com.example.outsourcing.Store.entity.Store;
import com.example.outsourcing.eunm.OrdersStatus;
import com.example.outsourcing.exception.OrdersErrorCode;
import com.example.outsourcing.exception.OrdersException;
import com.example.outsourcing.response.CommonResponseBody;
import com.example.outsourcing.sesstionUtils.SessionUtils;
import com.example.outsourcing.orders.dto.OrdersResponseDto;
import com.example.outsourcing.member.repository.MemberRepository;
import com.example.outsourcing.orders.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.time.LocalTime;
import java.util.List;

import static com.example.outsourcing.eunm.OrdersStatus.ORDERED;
import static com.example.outsourcing.eunm.OrdersStatus.REJECTED;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final SessionUtils sessionUtils;

    // 주문 생성 로직
    @Transactional
    public CommonResponseBody<OrdersResponseDto> createOrders(Long storeId, Long menuId) throws OrdersException {
        // 로그인한 고객을 주문한 사람으로 설정
        Member loggedInUser = memberRepository.findByIdOrElseThrow(sessionUtils.getLoggedInUserId());
        Menu findMenu = menuRepository.findByIdOrElseThrow(menuId);

        // 주문을 할 가게와 메뉴Id로 찾은 메뉴에 등록된 가게가 다를 경우
        if (findMenu.getStore().getId() != storeId) {
            throw new IllegalArgumentException("본 가게의 메뉴가 아닙니다.");
        }

        // 최소 주문 금액 미달시 예외처리
        if (findMenu.getStore().getPrice() > findMenu.getPrice()) {
            throw new OrdersException(OrdersErrorCode.FAIL_TO_MEET_MIN_PRICE);
        }

        // 가게 영업 시간이 아닐시 예외처리
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isBefore(findMenu.getStore().getOpenTime()) || currentTime.isAfter(findMenu.getStore().getCloseTime())) {
            throw new OrdersException(OrdersErrorCode.NOT_BUSINESS_HOURS);
        }

        Orders orders = Orders.builder()
                .status(ORDERED)
                .member(loggedInUser)
                .store(findMenu.getStore())
                .menu(findMenu)
                .build();

        ordersRepository.save(orders);

        return new CommonResponseBody<>("주문이 등록되었습니다.",OrdersResponseDto.toDto(orders), HttpStatus.CREATED);
    }

    // 특정 가게에 등록된 전체 주문 조회 로직
    @Transactional
    public CommonResponseBody<List<OrdersResponseDto>> findAllOrders(Long storeId) throws OrdersException {
        // 로그인한 유저가 가게의 Owner 일때만 조회 가능, 로그인한 유저로 가게 오너인지 확인하는 코드 필요
        Store findStore = storeRepository.findByIdOrElseThrow(storeId);

        if (!findStore.getMember().getId().equals(sessionUtils.getLoggedInUserId())) {
            throw new OrdersException(OrdersErrorCode.NOT_ALLOWED_ACCESS);
        }

        List<Orders> allOrders = ordersRepository.findAllByStoreId(storeId);
        return new CommonResponseBody<>("조회에 성공했습니다.", allOrders.stream().map(OrdersResponseDto::toDto).toList(), HttpStatus.OK);
    }

    // 특정 가게에 등록된 단일 주문 조회 로직
    @Transactional
    public CommonResponseBody<OrdersResponseDto> findOrders(Long storeId, Long ordersId) throws OrdersException {
        // 로그인한 유저가 고객이거나 Owner 일때만 조회가능, 로그인한 유저가 가게 오너이거나 주문한 고객인지 확인하는 코드 필요
        Orders findOrders = ordersRepository.findByIdOrElseThrow(ordersId);
        if (!findOrders.getMember().getId().equals(sessionUtils.getLoggedInUserId()) && !findOrders.getStore().getMember().getId().equals(sessionUtils.getLoggedInUserId())) {
            throw new OrdersException(OrdersErrorCode.NOT_ALLOWED_ACCESS);
        }

        if (findOrders.getStore().getId() != storeId) {
            throw new IllegalArgumentException("본 가게의 주문이 아니므로 조회할 수 없습니다.");
        }

        return new CommonResponseBody<>("조회에 성공했습니다.",OrdersResponseDto.toDto(findOrders), HttpStatus.OK);
    }

    // 특정 가게에 등록된 주문상태 변경 로직
    @Transactional
    public CommonResponseBody<OrdersResponseDto> updateOrdersStatus(Long storeId, Long ordersId, OrdersStatus status, String rejectReason) throws OrdersException {
        //로그인한 유저가 Owner 일때만 변경 가능, 로그인한 유저가 가게오너인지 확인하는 코드 필요
        Orders findOrders = ordersRepository.findByIdOrElseThrow(ordersId);

        if (!findOrders.getStore().getMember().getId().equals(sessionUtils.getLoggedInUserId())) {
            throw new OrdersException(OrdersErrorCode.NOT_ALLOWED_ACCESS);
        }

        if (findOrders.getStore().getId() != storeId) {
            throw new IllegalArgumentException("본 가게의 주문이 아니므로 주문 상태를 변경할 수 없습니다.");
        }

        if (findOrders.getStatus() == REJECTED) {
            throw new OrdersException(OrdersErrorCode.ALREADY_REJECTED);
        }

        findOrders.updateStatus(status, rejectReason);
        ordersRepository.save(findOrders);

        return new CommonResponseBody<>("정상적으로 상태가 변경되었습니다.",OrdersResponseDto.toDto(findOrders), HttpStatus.OK);
    }
}
