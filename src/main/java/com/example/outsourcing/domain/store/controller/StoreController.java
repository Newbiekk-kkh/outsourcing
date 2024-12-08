package com.example.outsourcing.domain.store.controller;

import com.example.outsourcing.domain.store.dto.*;
import com.example.outsourcing.global.common.CommonResponseBody;
import com.example.outsourcing.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/owner")
    public CommonResponseBody<StoreResponseDto> createStore (@RequestBody StoreRequestDto requestDto, @SessionAttribute("id") Long memberId) {

        return new CommonResponseBody<>("가게 생성 완료", storeService.save(requestDto, memberId));
    }




    @GetMapping
    public CommonResponseBody<List<StoreResponseDto>> findStores (@RequestBody StoreFindRequestDto storeFindRequestDto) {

        return new CommonResponseBody<>("가게 조회 완료", storeService.findStores(storeFindRequestDto));
    }


    @GetMapping("/{storeId}")
    public CommonResponseBody<StoreDetailResponseDto> findStoreDetail (@PathVariable Long storeId) {

        return new CommonResponseBody<>("가게 상세 정보 조회",storeService.findStoreDetail(storeId));
    }


    @PatchMapping("/owner/{storeId}")
    public CommonResponseBody<String> updateStore (@PathVariable Long storeId,
                                                   @RequestBody StoreRequestDto requestDto,
                                                   @SessionAttribute("id") Long memberId) {

        storeService.updateStore(requestDto, storeId, memberId);

        return new CommonResponseBody<>("가게 정보가 수정되었습니다",null);
    }

    @DeleteMapping("/owner/{storeId}")
    public CommonResponseBody<String> deleteStore (@PathVariable Long storeId,
                                                                   @RequestBody StoreDeleteDto storeDeleteDto,
                                                                   @SessionAttribute("id") Long memberId) {

        storeService.deleteStore(storeDeleteDto, storeId, memberId);
        return (new CommonResponseBody<>("폐업 처리가 완료 되었습니다", null));
    }

}
