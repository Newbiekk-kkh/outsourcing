package com.example.outsourcing.orders;

import com.example.outsourcing.orders.dto.OrdersRequestDto;
import com.example.outsourcing.orders.dto.OrdersResponseDto;
import com.example.outsourcing.orders.dto.UpdateOrdersRequestDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store/{storeId}/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<OrdersResponseDto> createOrders(@PathVariable Long storeId, @RequestBody OrdersRequestDto dto) {
        OrdersResponseDto ordersResponseDto = ordersService.createOrders(storeId, dto.getMenuId());
        return new ResponseEntity<>(ordersResponseDto , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrdersResponseDto>> findAllOrders(@PathVariable Long storeId) {
        List<OrdersResponseDto> allOrdersResponseDtoList = ordersService.findAllOrders(storeId);
        return new ResponseEntity<>(allOrdersResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{ordersId}")
    public ResponseEntity<OrdersResponseDto> findOrders(@PathVariable Long storeId, @PathVariable Long ordersId) {
        OrdersResponseDto findOrdersResponseDto = ordersService.findOrders(storeId, ordersId);
        return new ResponseEntity<>(findOrdersResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{ordersId}")
    public ResponseEntity<OrdersResponseDto> updateOrdersStatus(
            @PathVariable Long storeId,
            @PathVariable Long ordersId,
            @RequestBody UpdateOrdersRequestDto dto) {
        OrdersResponseDto updatedOrdersResponseDto = ordersService.updateOrdersStatus(storeId, ordersId, dto.getStatus());
        return new ResponseEntity<>(updatedOrdersResponseDto, HttpStatus.OK);
    }
}
