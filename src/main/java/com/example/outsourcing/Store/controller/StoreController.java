package com.example.outsourcing.Store.controller;

import com.example.outsourcing.Store.dto.*;
import com.example.outsourcing.response.CommonResponseBody;
import com.example.outsourcing.Store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
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


    @PatchMapping("/{storeId}")
    public CommonResponseBody<String> updateStore (@PathVariable Long storeId,
                                                                   @RequestBody StoreRequestDto requestDto) {

        storeService.updateStore(requestDto, storeId);

        return new CommonResponseBody<>("가게 정보가 수정되었습니다",null);
    }

    @DeleteMapping("/{storeId}")
    public CommonResponseBody<String> deleteStore (@PathVariable Long storeId,
                                                                   @RequestBody StoreDeleteDto storeDeleteDto,
                                                                   @SessionAttribute("id") Long memberId) {
        storeService.deleteStore(storeDeleteDto, storeId, memberId);
        return (new CommonResponseBody<>("폐업 처리가 완료 되었습니다", null));
    }

}
