package com.wanted.backend.member.entity.mapper;

import com.wanted.backend.member.entity.Member;
import com.wanted.backend.member.entity.dto.response.MemberResponse;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toEntity(final String email, final String password) {
        return Member.builder()
            .email(email)
            .password(password)
            .build();
    }

    public MemberResponse toResponse(final Member member, final String token) {
        return MemberResponse.builder()
            .id(member.getId())
            .email(member.getEmail())
            .token(token)
            .build();
    }

}
