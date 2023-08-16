package com.wanted.backend.global.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberTokenInfo {

    private final Long id;
    private final String email;
}

