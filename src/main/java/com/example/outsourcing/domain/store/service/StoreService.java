package com.example.outsourcing.domain.store.service;

import com.example.outsourcing.domain.menu.dto.GetMenuResponseDto;
import com.example.outsourcing.domain.store.dto.*;
import com.example.outsourcing.domain.member.entity.Member;
import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.global.enums.StoreStatus;
import com.example.outsourcing.domain.store.repository.StoreRepository;
import com.example.outsourcing.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "영업중인 가게는 최대 3개까지 가질 수 있습니다.");
        }

        Store store = storeRepository.save(Store.builder()
                .name(dto.getName())
                .openTime(dto.getOpenTime())
                .closeTime(dto.getCloseTime())
                .price(dto.getDefaultPrice())
                .member(member)
                .build()
        );

        return new StoreResponseDto(store);


    }


    public List<StoreResponseDto> findStores(StoreFindRequestDto storeFindRequestDto) {
        String name = storeFindRequestDto.getName();
        List<Store> storeList;

        // 입력값이 없으면 전체 조회, 있으면 입력값을 포함한 식당만 조회
        if (name == null || name.trim().isEmpty()) {
            storeList = storeRepository.findAll();
        } else {
            storeList = storeRepository.findByNameContaining(storeFindRequestDto.getName());
        }


        return storeList.stream()
                .map(StoreResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public StoreDetailResponseDto findStoreDetail(Long storeId) {

        Store store = storeRepository.findByIdOrElseThrow(storeId);
        List<GetMenuResponseDto> menuResponseDto = store.getMenus().stream()
                .map(GetMenuResponseDto::getMenuResponse)
                .collect(Collectors.toList());


        return StoreDetailResponseDto.of(store, menuResponseDto);

    }


    @Transactional
    public void updateStore(StoreRequestDto requestDto, Long storeId, Long memberId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);

        Store store = storeRepository.findByIdOrElseThrow(storeId);

        if (store.getMember() != member) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "가게를 생성한 멤버에게만 권한이 있습니다");
        }
        store.update(requestDto);
    }


    @Transactional
    public void deleteStore(StoreDeleteDto storeDeleteDto, Long storeId, Long memberId) {

        Store store = storeRepository.findByIdOrElseThrow(storeId);
        Member member = memberRepository.findByIdOrElseThrow(memberId);

        if (store.getMember() != member) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "가게를 생성한 멤버에게만 권한이 있습니다");
        }

        Pattern validPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher validPassMatcher = validPattern.matcher(storeDeleteDto.getEmail());

        if (!validPassMatcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일 형식이 아닙니다.");
        }

        if (Objects.equals(member.getEmail(), storeDeleteDto.getEmail())) {
            store.delete();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일이 일치하지 않습니다.");
        }

    }
}
