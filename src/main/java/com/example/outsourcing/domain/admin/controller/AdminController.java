package com.example.outsourcing.domain.admin.controller;

import com.example.outsourcing.domain.admin.dto.AdminStatsResponseDto;
import com.example.outsourcing.domain.admin.service.AdminService;
import com.example.outsourcing.global.common.CommonResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public CommonResponseBody<AdminStatsResponseDto> getDailyStats() {

        return new CommonResponseBody<>("통계 조회 완료", adminService.getDailyStats(), HttpStatus.OK);
    }
}
