package com.example.outsourcing.domain.orders.controller;

import com.example.outsourcing.domain.orders.dto.OrdersCountByMemberDto;
import com.example.outsourcing.global.exception.OrdersException;
import com.example.outsourcing.domain.orders.service.OrdersService;
import com.example.outsourcing.domain.orders.dto.OrdersRequestDto;
import com.example.outsourcing.domain.orders.dto.OrdersResponseDto;
import com.example.outsourcing.domain.orders.dto.UpdateOrdersRequestDto;
import com.example.outsourcing.global.common.CommonResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/orders")
public class OrdersController {
    private final OrdersService ordersService;

    /**
     * 주문 등록
     * @param storeId 주문할 가게의 Id
     * @param dto OrdersRequestDto 주문할 메뉴 dto
     * @return 201 성공시 CREATED, 실패시 상황에 맞는 에러코드
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponseBody<OrdersResponseDto> createOrders(@PathVariable Long storeId, @RequestBody OrdersRequestDto dto) throws OrdersException {
        return ordersService.createOrders(storeId, dto.getMenuId());
    }

    /**
     * 특정 가게에 등록된 주문 전체 조회
     * @param storeId 주문이 등록된 가게 Id
     * @return 성공시 200 OK, 실패시 상황에 맞는 에러코드
     */
    @GetMapping
    public CommonResponseBody<List<OrdersResponseDto>> findAllOrders(@PathVariable Long storeId) throws OrdersException {
        return ordersService.findAllOrders(storeId);
    }

    /**
     * 특정 가게에 등록된 특정 주문 조회
     * @param storeId 주문이 등록된 가게 Id
     * @param ordersId 조회할 주문 Id
     * @return 성공시 200 OK, 실패시 상황에 맞는 에러코드
     */
    @GetMapping("/{ordersId}")
    public CommonResponseBody<OrdersResponseDto> findOrders(@PathVariable Long storeId, @PathVariable Long ordersId) throws OrdersException {
        return ordersService.findOrders(storeId,ordersId);
    }

    /**
     * 특정 주문을 한 고객이 현재 가게에 주문을 몇번했는지 조회
     * @param storeId 주문이 등록된 가게 Id
     * @param ordersId 조회할 주문 Id
     * @return 성공시 200 OK, 실패시 상황에 맞는 에러코드
     * @throws OrdersException
     */
    @GetMapping("/{ordersId}/members")
    public CommonResponseBody<OrdersCountByMemberDto> countOrdersByMember(@PathVariable Long storeId, @PathVariable Long ordersId) throws OrdersException {
        return ordersService.countOrdersByMember(storeId,ordersId);
    }

    /**
     * 특정 주문의 주문 상태 업데이트
     * @param storeId 주문이 등록된 가게 Id
     * @param ordersId 수정할 주문 Id
     * @param dto UpdateOrdersRequestDto 주문의 상태 변경을 담은 Dto
     * @return 성공시 200 OK, 실패시 상황에 맞는 에러코드
     */
    @PatchMapping("/{ordersId}")
    public CommonResponseBody<OrdersResponseDto> updateOrdersStatus(
            @PathVariable Long storeId,
            @PathVariable Long ordersId,
            @RequestBody UpdateOrdersRequestDto dto) throws OrdersException {
        return ordersService.updateOrdersStatus(storeId, ordersId, dto.getStatus(), dto.getRejectReason());
    }
}
