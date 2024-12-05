package com.example.outsourcing.config;

import com.example.outsourcing.member.entity.Member;
import com.example.outsourcing.eunm.MemberAccess;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AdminRoleInterceptor implements HandlerInterceptor {
    
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws UnauthorizedException {
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "세션이 끊어졌습니다.");
//        }
//
//        Authentication authentication = (Authentication) session.getAttribute(SessionNames.USER_AUTH);
//        Member member = authentication.getMember();
//
//        if (member.getAccess() != MemberAccess.OWNER) {
//            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "권한이 필요합니다.");
//        }
//
//        return true;
//    }
}