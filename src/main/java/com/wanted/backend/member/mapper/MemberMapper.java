package com.wanted.backend.member.mapper;

import com.wanted.backend.member.dto.request.MemberRequest;
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

}
