package com.example.outsourcing.domain.admin.service;

import com.example.outsourcing.domain.admin.dto.AdminStatsResponseDto;
import com.example.outsourcing.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final OrderRepository orderRepository;

    public AdminStatsResponseDto getDailyStats() {
//        List<Orders> ordersList= orderRepository.findByStatus(OrdersStatus.DELIVERED);


        return new AdminStatsResponseDto();
    }
}
