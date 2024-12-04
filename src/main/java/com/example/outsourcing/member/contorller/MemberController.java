package com.example.outsourcing.member.contorller;


import com.example.outsourcing.common.SuccessResponse;
import com.example.outsourcing.member.dto.DeleteRequestDto;
import com.example.outsourcing.member.dto.MemberRequestDto;
import com.example.outsourcing.member.dto.MemberResponseDto;
import com.example.outsourcing.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public SuccessResponse<MemberResponseDto> createUser(@RequestBody MemberRequestDto requestDto){
        return memberService.createUser(requestDto);
    }

    @PostMapping("/login")
    public SuccessResponse<?> login(@RequestBody MemberRequestDto requestDto, HttpServletRequest request){
        HttpSession existingSession =request.getSession(false);
        if(existingSession != null && existingSession.getAttribute("id") != null) {
            log.info("이미 로그인된 세션이 존재합니다: User ID {}", existingSession.getAttribute("id"));
            throw new IllegalArgumentException("이미 로그인 되어있습니다.");
        }

        Long userId = memberService.authenticateAndGetId(requestDto.getEmail(), requestDto.getPassword());
        log.debug("authenticateAndGetId결과{}", userId);

        if(userId != null) {
            //새로운 세션 생성
            HttpSession session = request.getSession(true);
            session.setAttribute("id", userId);
            log.info("로그인 성공 : User ID{}", userId);
            return SuccessResponse.of("로그인 성공" , "");
        } else {
            log.info("로그인 실패 : {} ", requestDto.getEmail());
            throw new IllegalArgumentException("이메일 혹은 비밀번호가 틀립니다.");
        }
    }

    @PatchMapping
    public SuccessResponse<?> deleteUser(@RequestBody DeleteRequestDto requestDto, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute(requestDto.getId().toString());
        return memberService.deleteUser(requestDto);
    }
}
