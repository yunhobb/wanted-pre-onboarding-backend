package com.wanted.backend.member.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSignUpRequest {

    @Email
    private String email;

    @Size(min = 8)
    private String password;
}
