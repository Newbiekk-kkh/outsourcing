package com.example.outsourcing.global.sesstionUtils;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class SessionUtils {
    private final HttpSession session;

    // 로그인한 유저 ID 가져오기
    public Long getLoggedInUserId() {
        Long loggedInUserId = (Long) session.getAttribute("id");

        if (loggedInUserId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }
        return loggedInUserId;
    }
}
