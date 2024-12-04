package com.example.outsourcing.controller;

import com.example.outsourcing.dto.StoreCreateRequestDto;
import com.example.outsourcing.dto.StoreResponseDto;
import com.example.outsourcing.entity.Member;
import com.example.outsourcing.response.CommonResponseBody;
import com.example.outsourcing.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<CommonResponseBody<StoreResponseDto>> createStore (@RequestBody StoreCreateRequestDto createRequestDto, @SessionAttribute(AuthConstants.LOGIN_USER) Member member) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseBody<>(storeService.save(createRequestDto, member).toString()));
    }




    @GetMapping
    public ResponseEntity<CommonResponseBody<List<StoreResponseDto>>> findStores (@RequestBody StoreFindRequestDto storeFindRequestDto),






}
