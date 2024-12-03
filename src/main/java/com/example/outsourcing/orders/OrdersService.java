package com.example.outsourcing.orders;

import com.example.outsourcing.entity.Member;
import com.example.outsourcing.entity.Menu;
import com.example.outsourcing.entity.Orders;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.orders.dto.OrdersResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.outsourcing.eunm.OrderStatus.ORDERED;

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
}
