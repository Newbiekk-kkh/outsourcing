package com.example.outsourcing.domain.order.controller;

import com.example.outsourcing.domain.order.dto.OrderCountByMemberDto;
import com.example.outsourcing.global.exception.OrderException;
import com.example.outsourcing.domain.order.service.OrderService;
import com.example.outsourcing.domain.order.dto.OrderRequestDto;
import com.example.outsourcing.domain.order.dto.OrderResponseDto;
import com.example.outsourcing.domain.order.dto.UpdateOrderRequestDto;
import com.example.outsourcing.global.common.CommonResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/orders")
public class OrderController {
    private final OrderService orderService;

    /**
     * 주문 등록
     * @param storeId 주문할 가게의 Id
     * @param dto OrdersRequestDto 주문할 메뉴 dto
     * @return 201 성공시 CREATED, 실패시 상황에 맞는 에러코드
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponseBody<OrderResponseDto> createOrders(@PathVariable Long storeId, @RequestBody OrderRequestDto dto) throws OrderException {
        return orderService.createOrders(storeId, dto.getMenuId());
    }

    /**
     * 특정 가게에 등록된 주문 전체 조회
     * @param storeId 주문이 등록된 가게 Id
     * @return 성공시 200 OK, 실패시 상황에 맞는 에러코드
     */
    @GetMapping
    public CommonResponseBody<List<OrderResponseDto>> findAllOrders(@PathVariable Long storeId) throws OrderException {
        return orderService.findAllOrders(storeId);
    }

    /**
     * 특정 가게에 등록된 특정 주문 조회
     * @param storeId 주문이 등록된 가게 Id
     * @param orderId 조회할 주문 Id
     * @return 성공시 200 OK, 실패시 상황에 맞는 에러코드
     */
    @GetMapping("/{orderId}")
    public CommonResponseBody<OrderResponseDto> findOrders(@PathVariable Long storeId, @PathVariable Long orderId) throws OrderException {
        return orderService.findOrders(storeId, orderId);
    }

    /**
     * 특정 주문을 한 고객이 현재 가게에 주문을 몇번했는지 조회
     * @param storeId 주문이 등록된 가게 Id
     * @param orderId 조회할 주문 Id
     * @return 성공시 200 OK, 실패시 상황에 맞는 에러코드
     * @throws OrderException
     */
    @GetMapping("/{orderId}/order-counts")
    public CommonResponseBody<OrderCountByMemberDto> countOrdersByMember(@PathVariable Long storeId, @PathVariable Long orderId) throws OrderException {
        return orderService.countOrdersByMember(storeId, orderId);
    }

    /**
     * 특정 주문의 주문 상태 업데이트
     * @param storeId 주문이 등록된 가게 Id
     * @param orderId 수정할 주문 Id
     * @param dto UpdateOrdersRequestDto 주문의 상태 변경을 담은 Dto
     * @return 성공시 200 OK, 실패시 상황에 맞는 에러코드
     */
    @PatchMapping("/{orderId}")
    public CommonResponseBody<OrderResponseDto> updateOrdersStatus(
            @PathVariable Long storeId,
            @PathVariable Long orderId,
            @RequestBody UpdateOrderRequestDto dto) throws OrderException {
        return orderService.updateOrdersStatus(storeId, orderId, dto.getStatus(), dto.getRejectReason());
    }
}
