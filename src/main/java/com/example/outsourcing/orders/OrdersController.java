package com.example.outsourcing.orders;

import com.example.outsourcing.orders.dto.OrdersRequestDto;
import com.example.outsourcing.orders.dto.OrdersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
