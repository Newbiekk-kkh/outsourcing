package com.example.outsourcing.member.service;

import com.example.outsourcing.common.SuccessResponse;
import com.example.outsourcing.common.UserAccess;
import com.example.outsourcing.common.UserStatus;
import com.example.outsourcing.config.PasswordEncoder;
import com.example.outsourcing.member.dto.DeleteRequestDto;
import com.example.outsourcing.member.dto.MemberRequestDto;
import com.example.outsourcing.member.dto.MemberResponseDto;
import com.example.outsourcing.member.entity.Member;
import com.example.outsourcing.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SuccessResponse<MemberResponseDto> createUser(MemberRequestDto requestDto) {
        if(memberRepository.existsByEmail(requestDto.getEmail())){
            return SuccessResponse.of("중복되거나 탈퇴한 이메일입니다.", null);
        }

        //이메일 형식 검사
        Pattern validPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher validPassMatcher = validPattern.matcher(requestDto.getEmail());

        if (!validPassMatcher.find()) {
            return SuccessResponse.of("이메일 형식이 아닙니다.", null);
        }

        //비밀번호 형식 검사
        validPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&^])[A-Za-z\\d@!%*#?&^]{8,}$");
        validPassMatcher = validPattern.matcher(requestDto.getPassword());

        if (!validPassMatcher.find()) {
            return SuccessResponse.of("비밀번호는 영문+특수문자+숫자 8자로 구성되어야 합니다", null);
        }

        // 비밀번호 암호화 및 회원 생성
        requestDto = encryptedPassword(requestDto);
        log.debug("암호화된 비밀번호: {}", requestDto.getPassword());
        Member member = null;


        if (requestDto.getAccess().equals("유저")) {
            member = new Member(requestDto.getEmail(), requestDto.getPassword(), UserAccess.CLIENT.getUserAccess(), UserStatus.EXISTENCE.getUserStatus());
        }
        if(requestDto.getAccess().equals("사장님")) {
            member = new Member(requestDto.getEmail(), requestDto.getPassword(), UserAccess.MANAGER.getUserAccess(), UserStatus.EXISTENCE.getUserStatus());
        }

        log.debug("Entity 생성 후 비밀번호: {}", member.getPassword());

        Member savedMember = memberRepository.save(member);
        log.debug("저장된 Member 비밀번호: {}", savedMember.getPassword());

        return SuccessResponse.of("회원 가입이 완료되었습니다.", MemberResponseDto.toDto(savedMember));
    }

    //로그인
    public Long authenticateAndGetId(String email, String password) {
        // 이메일로 사용자를 조회
        Member member = getByMemberByEmail(email);

        if (passwordEncoder.matches(password, member.getPassword())) {
            return member.getId();
        } else {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    public SuccessResponse<?> deleteUser(DeleteRequestDto dto) {
        Member member = memberRepository.findById(dto.getId()).orElseThrow();

        if (passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            member.delete(UserStatus.SECESSION);
            memberRepository.save(member);
        } else {
            return SuccessResponse.of("비밀번호가 틀립니다.", "");
        }

        return SuccessResponse.of("회원 탈퇴가 완료되었습니다.", "");
    }

    // 이메일로 멤버 조회 (제한적으로 사용)
    public Member getByMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일이 존재하지 않습니다."));
    }

    // 비밀번호 암호화
    private MemberRequestDto encryptedPassword(MemberRequestDto memberDto) {
        if (memberDto.getPassword() != null && !memberDto.getPassword().isEmpty()) {
            String rawPassword = memberDto.getPassword();
            String encodedPassword = passwordEncoder.encode(rawPassword);
            log.debug("원문 비밀번호: {}", rawPassword);
            log.debug("암호화된 비밀번호: {}", encodedPassword);
            memberDto.setPassword(encodedPassword);
        }
        return memberDto;
    }
}
