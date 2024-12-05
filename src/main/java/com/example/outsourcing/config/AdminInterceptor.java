package com.example.outsourcing.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String memberType = request.getHeader("id"); // 예: 헤더로 Member-Type 전달

        log.info("{} --------------- {}",request, response);
//        if (memberType == null || !memberType.equals("OWNER")) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
//            response.getWriter().write("Only OWNER can manage stores.");
//            return false; // 요청 진행 중단
//        }
//
        return true;
    }
}