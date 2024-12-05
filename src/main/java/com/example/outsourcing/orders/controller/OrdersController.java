package com.example.outsourcing.orders.controller;

import com.example.outsourcing.exception.OrdersException;
import com.example.outsourcing.orders.service.OrdersService;
import com.example.outsourcing.orders.dto.OrdersRequestDto;
import com.example.outsourcing.orders.dto.OrdersResponseDto;
import com.example.outsourcing.orders.dto.UpdateOrdersRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * @return ordersResponseDto, 201 성공시 CREATED, 실패시 상황에 맞는 에러코드
     */
    @PostMapping
    public ResponseEntity<OrdersResponseDto> createOrders(@PathVariable Long storeId, @RequestBody OrdersRequestDto dto) throws OrdersException {
        OrdersResponseDto ordersResponseDto = ordersService.createOrders(storeId, dto.getMenuId());
        return new ResponseEntity<>(ordersResponseDto , HttpStatus.CREATED);
    }

    /**
     * 특정 가게에 등록된 주문 전체 조회
     * @param storeId 주문이 등록된 가게 Id
     * @return allOrdersResponseDtoList, 성공시 200 OK, 실패시 상황에 맞는 에러코드
     */
    @GetMapping
    public ResponseEntity<List<OrdersResponseDto>> findAllOrders(@PathVariable Long storeId) throws OrdersException {
        List<OrdersResponseDto> allOrdersResponseDtoList = ordersService.findAllOrders(storeId);
        return new ResponseEntity<>(allOrdersResponseDtoList, HttpStatus.OK);
    }

    /**
     * 특정 가게에 등록된 특정 주문 조회
     * @param storeId 주문이 등록된 가게 Id
     * @param ordersId 조회할 주문 Id
     * @return findOrdersResponseDto, 성공시 200 OK, 실패시 상황에 맞는 에러코드
     */
    @GetMapping("/{ordersId}")
    public ResponseEntity<OrdersResponseDto> findOrders(@PathVariable Long storeId, @PathVariable Long ordersId) throws OrdersException {
        OrdersResponseDto findOrdersResponseDto = ordersService.findOrders(storeId, ordersId);
        return new ResponseEntity<>(findOrdersResponseDto, HttpStatus.OK);
    }

    /**
     * 특정 주문의 주문 상태 업데이트
     * @param storeId 주문이 등록된 가게 Id
     * @param ordersId 수정할 주문 Id
     * @param dto UpdateOrdersRequestDto 주문의 상태 변경을 담은 Dto
     * @return updatedOrdersResponseDto, 성공시 200 OK, 실패시 상황에 맞는 에러코드
     */
    @PatchMapping("/{ordersId}")
    public ResponseEntity<OrdersResponseDto> updateOrdersStatus(
            @PathVariable Long storeId,
            @PathVariable Long ordersId,
            @RequestBody UpdateOrdersRequestDto dto) throws OrdersException {
        OrdersResponseDto updatedOrdersResponseDto = ordersService.updateOrdersStatus(storeId, ordersId, dto.getStatus(), dto.getRejectReason());
        return new ResponseEntity<>(updatedOrdersResponseDto, HttpStatus.OK);
    }
}
