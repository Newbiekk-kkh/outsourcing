package com.example.outsourcing.Store.service;

import com.example.outsourcing.Store.dto.*;
import com.example.outsourcing.member.entity.Member;
import com.example.outsourcing.Store.entity.Store;
import com.example.outsourcing.eunm.StoreStatus;
import com.example.outsourcing.Store.repository.StoreRepository;
import com.example.outsourcing.member.repository.MemberRepository;
import com.example.outsourcing.response.CommonResponseBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public StoreResponseDto save(StoreRequestDto dto, Long memberId) {

        Member member = memberRepository.findByIdOrElseThrow(memberId);

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
                .member(member)
                .build()
        );

        return new StoreResponseDto(store);


    }


    public List<StoreResponseDto> findStores(StoreFindRequestDto storeFindRequestDto) {

        List<Store> storeList = storeRepository.findByNameContaining(storeFindRequestDto.getName());

        return storeList.stream()
                .map(StoreResponseDto::new)
                .collect(Collectors.toList());
    }

    public StoreDetailResponseDto findStoreDetail(Long storeId) {

        Store store = storeRepository.findByIdOrElseThrow(storeId);

        return new StoreDetailResponseDto(store);

    }


    @Transactional
    public void updateStore(StoreRequestDto requestDto, Long storeId) {

        Store store = storeRepository.findByIdOrElseThrow(storeId);

        store.update(requestDto);
    }


    @Transactional
    public void deleteStore(StoreDeleteDto storeDeleteDto, Long storeId, Long memberId) {

        Store store = storeRepository.findByIdOrElseThrow(storeId);
        Member member = memberRepository.findByIdOrElseThrow(memberId);

        if (Objects.equals(member.getEmail(), storeDeleteDto.getEmail())) {
            store.delete();
        } else {
            throw new IllegalStateException("이메일이 일치하지 않습니다");
        }

    }
}
