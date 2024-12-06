package com.example.outsourcing.global.config;

import com.example.outsourcing.domain.order.entity.Order;
import com.example.outsourcing.domain.order.repository.OrderRepository;
import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.domain.store.repository.StoreRepository;
import com.example.outsourcing.global.enums.OrderStatus;
import com.example.outsourcing.global.enums.UserAccess;
import com.example.outsourcing.global.enums.UserStatus;
import com.example.outsourcing.domain.member.entity.Member;
import com.example.outsourcing.domain.member.repository.MemberRepository;
import com.example.outsourcing.domain.menu.entity.Menu;
import com.example.outsourcing.domain.menu.repository.MenuRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

import static com.example.outsourcing.global.enums.MenuStatus.NORMAL;

@Slf4j
@Component
@Profile("dev")
public class DataInitializer {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrderRepository orderRepository;

    @PostConstruct
    public void init() {
        Member member1 = new Member("asd@asd.com", passwordEncoder.encode("!QweQwe123"), UserAccess.MANAGER, UserStatus.SECESSION);
        Member member2 = new Member("qwe@qwe.com", passwordEncoder.encode("!QweQwe123"), UserAccess.CLIENT, UserStatus.SECESSION);
        memberRepository.save(member1);
        memberRepository.save(member2);

        Store store = Store.builder()
                .name("김밥천국")
                .openTime(LocalTime.of(10,0,0))
                .closeTime(LocalTime.of(22,0,0))
                .price(5000)
                .member(member1)
                .build();

        storeRepository.save(store);

        Menu menu = new Menu("볶음밥", "맛있다", 8000, NORMAL, store);

        menuRepository.save(menu);

        Order orders = new Order(OrderStatus.DELIVERED, member2, store, menu);

        orderRepository.save(orders);

    }
}
