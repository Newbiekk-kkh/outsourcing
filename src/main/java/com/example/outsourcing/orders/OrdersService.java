package com.example.outsourcing.orders;

import com.example.outsourcing.entity.Member;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Orders;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.orders.dto.OrdersResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.outsourcing.eunm.OrdersStatus.ORDERED;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Transactional
    public OrdersResponseDto createOrders(Long storeId, Long menuId) {
        // 로그인한 고객을 주문한 사람으로 설정, 현재 기능별 개발중으로 다른 주요 기능들이 개발되지 않아 임시객체로 사용
        Member member = new Member();
        Store store = new Store();
        Menu menu = new Menu();

        Orders orders = new Orders(ORDERED);
        orders.setMember(member);
        orders.setStore(store);
        orders.setMenu(menu);

        ordersRepository.save(orders);

        return OrdersResponseDto.toDto(orders);
    }

    public List<OrdersResponseDto> findAllOrders(Long storeId) {
        // 로그인한 유저가 가게의 Owner 일때만 조회 가능, 로그인한 유저로 가게 오너인지 확인하는 코드 필요

        List<Orders> allOrders = ordersRepository.findAllByStoreId(storeId);
        return allOrders.stream().map(OrdersResponseDto::toDto).toList();
    }

    public OrdersResponseDto findOrders(Long storeId, Long ordersId) {
        // 로그인한 유저가 고객이거나 Owner 일때만 조회가능, 로그인한 유저가 가게 오너이거나 주문한 고객인지 확인하는 코드 필요

        Orders findOrders = ordersRepository.findByIdOrElseThrow(ordersId);
        return OrdersResponseDto.toDto(findOrders);
    }
}
