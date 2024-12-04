package com.example.outsourcing.service;

import com.example.outsourcing.dto.StoreCreateRequestDto;
import com.example.outsourcing.dto.StoreResponseDto;
import com.example.outsourcing.entity.Member;
import com.example.outsourcing.entity.Store;
import com.example.outsourcing.eunm.StoreStatus;
import com.example.outsourcing.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;


    @Transactional
    public StoreResponseDto save(StoreCreateRequestDto dto, Member member) {

        long activeStoreCount = member.getStores().stream()
                .filter(store -> store.getStatus() == StoreStatus.NORMAL)
                .count();

        if (activeStoreCount >= 3) {
            throw new IllegalStateException("영업중인 가게는 최대 3개까지 가질 수 있습니다");
        }

        Store store = storeRepository.save(Store.builder()
                .name(dto.getName())
                .openTime(dto.getOpenTIme())
                .closeTime(dto.getCloseTime())
                .price(dto.getDefaultPrice())
                .build()
        );


        return new StoreResponseDto(store);


    }


}
