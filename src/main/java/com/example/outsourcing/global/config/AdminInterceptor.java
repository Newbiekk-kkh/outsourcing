package com.example.outsourcing.global.config;

import com.example.outsourcing.domain.member.entity.Member;
import com.example.outsourcing.domain.member.repository.MemberRepository;
import com.example.outsourcing.global.enums.UserAccess;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
public class AdminInterceptor implements HandlerInterceptor {
    private MemberRepository memberRepository;

    public AdminInterceptor(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "세션이 끊어졌습니다");
        }

        Long loggedInUserId = (Long) session.getAttribute("id");
        Member member = memberRepository.findByIdOrElseThrow(loggedInUserId);

        if (member.getUserAccess() != UserAccess.ADMIN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "관리자만 접근할 수 있습니다");
        }

        return true;
    }
}