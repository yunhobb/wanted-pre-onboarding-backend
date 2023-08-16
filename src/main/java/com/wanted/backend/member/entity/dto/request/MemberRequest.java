package com.wanted.backend.member.entity.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {

    @Email
    private String email;

    @Size(min = 8)
    private String password;
}
