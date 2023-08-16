package com.wanted.backend.member.entity.mapper;

import com.wanted.backend.member.entity.dto.request.MemberRequest;
import com.wanted.backend.member.entity.dto.response.MemberResponse;
import com.wanted.backend.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toEntity(final MemberRequest request) {
        return Member.builder()
            .email(request.getEmail())
            .password(request.getPassword())
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
