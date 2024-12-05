package com.example.outsourcing.config;

import com.example.outsourcing.Store.entity.Store;
import com.example.outsourcing.Store.repository.StoreRepository;
import com.example.outsourcing.member.entity.Member;
import com.example.outsourcing.member.repository.MemberRepository;
import com.example.outsourcing.menu.entity.Menu;
import com.example.outsourcing.menu.repository.MenuRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

import static com.example.outsourcing.eunm.MenuStatus.NORMAL;

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

    @PostConstruct
    public void init() {
        Member member1 = new Member("asd@asd.com", passwordEncoder.encode("!QweQwe123"), "사장님", "존재");
        Member member2 = new Member("qwe@qwe.com", passwordEncoder.encode("!QweQwe123"), "유저", "존재");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Store store = Store.builder()
                .name("김창배")
                .openTime(LocalTime.of(10,0,0))
                .closeTime(LocalTime.of(22,0,0))
                .price(5000L)
                .member(member1)
                .build();

        storeRepository.save(store);

        Menu menu = new Menu("볶음밥", "맛있다", 8000L, NORMAL, store);

        menuRepository.save(menu);
    }
}
