package com.example.outsourcing.domain.order.service;

import com.example.outsourcing.domain.order.dto.OrderCountByMemberDto;
import com.example.outsourcing.domain.store.repository.StoreRepository;
import com.example.outsourcing.domain.member.entity.Member;
import com.example.outsourcing.domain.menu.entity.Menu;
import com.example.outsourcing.domain.menu.repository.MenuRepository;
import com.example.outsourcing.domain.order.entity.Order;
import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.global.enums.OrderStatus;
import com.example.outsourcing.global.exception.OrderErrorCode;
import com.example.outsourcing.global.exception.OrderException;
import com.example.outsourcing.global.common.CommonResponseBody;
import com.example.outsourcing.global.sesstionUtils.SessionUtils;
import com.example.outsourcing.domain.order.dto.OrderResponseDto;
import com.example.outsourcing.domain.member.repository.MemberRepository;
import com.example.outsourcing.domain.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.time.LocalTime;
import java.util.List;

import static com.example.outsourcing.global.enums.OrderStatus.ORDERED;
import static com.example.outsourcing.global.enums.OrderStatus.REJECTED;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final SessionUtils sessionUtils;

    // 주문 생성 로직
    @Transactional
    public CommonResponseBody<OrderResponseDto> createOrders(Long storeId, Long menuId) throws OrderException {
        // 로그인한 고객을 주문한 사람으로 설정
        Member loggedInUser = memberRepository.findByIdOrElseThrow(sessionUtils.getLoggedInUserId());
        Menu findMenu = menuRepository.findByIdOrElseThrow(menuId);

        // 주문을 할 가게와 메뉴Id로 찾은 메뉴에 등록된 가게가 다를 경우
        if (findMenu.getStore().getId() != storeId) {
            throw new OrderException(OrderErrorCode.IS_NOT_MENU_OF_STORE);
        }

        // 최소 주문 금액 미달시 예외처리
        if (findMenu.getStore().getPrice() > findMenu.getPrice()) {
            throw new OrderException(OrderErrorCode.FAIL_TO_MEET_MIN_PRICE);
        }

        // 가게 영업 시간이 아닐시 예외처리
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isBefore(findMenu.getStore().getOpenTime()) || currentTime.isAfter(findMenu.getStore().getCloseTime())) {
            throw new OrderException(OrderErrorCode.NOT_BUSINESS_HOURS);
        }

        Order order = Order.builder()
                .status(ORDERED)
                .member(loggedInUser)
                .store(findMenu.getStore())
                .menu(findMenu)
                .build();

        orderRepository.save(order);

        return new CommonResponseBody<>("주문이 등록되었습니다.", OrderResponseDto.toDto(order), HttpStatus.CREATED);
    }

    // 특정 가게에 등록된 전체 주문 조회 로직
    @Transactional
    public CommonResponseBody<List<OrderResponseDto>> findAllOrders(Long storeId) throws OrderException {
        Store findStore = storeRepository.findByIdOrElseThrow(storeId);

        // 로그인한 유저가 가게의 Owner 일때만 조회 가능
        if (!findStore.getMember().getId().equals(sessionUtils.getLoggedInUserId())) {
            throw new OrderException(OrderErrorCode.NOT_ALLOWED_ACCESS);
        }

        List<Order> allOrders = orderRepository.findAllByStoreId(storeId);
        return new CommonResponseBody<>("조회에 성공했습니다.", allOrders.stream().map(OrderResponseDto::toDto).toList(), HttpStatus.OK);
    }

    // 특정 가게에 등록된 단일 주문 조회 로직
    @Transactional
    public CommonResponseBody<OrderResponseDto> findOrders(Long storeId, Long ordersId) throws OrderException {
        Order findOrder = orderRepository.findByIdOrElseThrow(ordersId);

        // 로그인한 유저가 고객이거나 Owner 일때만 조회가능
        if (!findOrder.getMember().getId().equals(sessionUtils.getLoggedInUserId()) && !findOrder.getStore().getMember().getId().equals(sessionUtils.getLoggedInUserId())) {
            throw new OrderException(OrderErrorCode.NOT_ALLOWED_ACCESS);
        }

        // 해당 가게의 주문이 아닐 때, 예외처리
        if (findOrder.getStore().getId() != storeId) {
            throw new OrderException(OrderErrorCode.IS_NOT_ORDER_OF_STORE);
        }

        return new CommonResponseBody<>("조회에 성공했습니다.", OrderResponseDto.toDto(findOrder), HttpStatus.OK);
    }

    // 특정 가게에 등록된 주문상태 변경 로직
    @Transactional
    public CommonResponseBody<OrderResponseDto> updateOrdersStatus(Long storeId, Long ordersId, OrderStatus status, String rejectReason) throws OrderException {
        // REJECTED 로 수정할땐 사유를 필수적으로 입력해야함
        if(status == REJECTED && rejectReason == null) {
            throw new OrderException(OrderErrorCode.REJECT_NEED_REASON);
        }

        Order findOrder = orderRepository.findByIdOrElseThrow(ordersId);

        // 로그인한 유저가 Owner 일때만 수정 가능
        if (!findOrder.getStore().getMember().getId().equals(sessionUtils.getLoggedInUserId())) {
            throw new OrderException(OrderErrorCode.NOT_ALLOWED_ACCESS);
        }

        // 해당 가게의 주문이 아닐 때, 예외처리
        if (findOrder.getStore().getId() != storeId) {
            throw new OrderException(OrderErrorCode.IS_NOT_ORDER_OF_STORE);
        }

        // 이미 REJECTED 인 주문은 더이상 수정할 수 없음
        if (findOrder.getStatus() == REJECTED) {
            throw new OrderException(OrderErrorCode.ALREADY_REJECTED);
        }

        findOrder.updateStatus(status, rejectReason);
        orderRepository.save(findOrder);

        return new CommonResponseBody<>("정상적으로 상태가 변경되었습니다.", OrderResponseDto.toDto(findOrder), HttpStatus.OK);
    }

    // 특정 가게에 주문을 한 고객의 그 가게에 주문한 횟수 보기
    @Transactional
    public CommonResponseBody<OrderCountByMemberDto> countOrdersByMember(Long storeId, Long ordersId) throws OrderException {
        Order findOrder = orderRepository.findByIdOrElseThrow(ordersId);

        // 해당 가게의 주문이 아닐 때, 예외처리
        if (findOrder.getStore().getId() != storeId) {
            throw new OrderException(OrderErrorCode.IS_NOT_ORDER_OF_STORE);
        }

        // 로그인한 유저가 Owner 일때만 조회 가능
        if (!findOrder.getStore().getMember().getId().equals(sessionUtils.getLoggedInUserId())) {
            throw new OrderException(OrderErrorCode.NOT_ALLOWED_ACCESS);
        }

        OrderCountByMemberDto ordersCountByMemberDto = orderRepository.countOrdersByMember(findOrder.getMember().getId());
        return new CommonResponseBody<>("조회에 성공했습니다.", ordersCountByMemberDto, HttpStatus.OK);
    }
}
