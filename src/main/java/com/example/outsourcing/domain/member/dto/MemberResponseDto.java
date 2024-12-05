package com.example.outsourcing.domain.member.dto;

import com.example.outsourcing.global.enums.UserAccess;
import com.example.outsourcing.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class MemberResponseDto {
    private final Long id;
    private final String email;
    private final UserAccess access;
    private final LocalDateTime modifiedAt;

    public static MemberResponseDto toDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .access(member.getUserAccess())
                .modifiedAt(member.getModifiedAt())
                .build();
    }
}
