package com.example.outsourcing.Store.controller;

import com.example.outsourcing.Store.dto.*;
import com.example.outsourcing.response.CommonResponseBody;
import com.example.outsourcing.Store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<CommonResponseBody<StoreResponseDto>> createStore (@RequestBody StoreRequestDto requestDto, @SessionAttribute("id") Long memberId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseBody<>(storeService.save(requestDto, memberId).toString()));
    }




    @GetMapping
    public ResponseEntity<CommonResponseBody<List<StoreResponseDto>>> findStores (@RequestBody StoreFindRequestDto storeFindRequestDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseBody<>(storeService.findStores(storeFindRequestDto).toString()));
    }


    @GetMapping("{storeId}")
    public ResponseEntity<CommonResponseBody<StoreDetailResponseDto>> findStoreDetail (@PathVariable Long storeId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseBody<>(storeService.findStoreDetail(storeId).toString()));
    }


    @PatchMapping("{storeId}")
    public ResponseEntity<CommonResponseBody<String>> updateStore (@PathVariable Long storeId,
                                                                   @RequestBody StoreRequestDto requestDto) {

        storeService.updateStore(requestDto, storeId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseBody<>("가게 정보가 수정되었습니다"));
    }

    @DeleteMapping
    public ResponseEntity<CommonResponseBody<String>> deleteStore (@PathVariable Long storeId,
                                                                   @RequestBody StoreDeleteDto storeDeleteDto,
                                                                   @SessionAttribute("id") Long memberId) {
        storeService.deleteStore(storeDeleteDto, storeId, memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseBody<>("폐업 처리가 완료 되었습니다"));
    }



}
