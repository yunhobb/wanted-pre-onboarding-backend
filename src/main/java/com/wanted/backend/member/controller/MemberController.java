package com.wanted.backend.member.controller;

import com.wanted.backend.member.dto.request.MemberSignUpRequest;
import com.wanted.backend.member.service.MemberService;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MemberController {

    MemberService memberService;

    @PostMapping("signup")
    public void signUp(@Valid @RequestBody MemberSignUpRequest request) {
        memberService.signUp(request);
    }

    @PostMapping("signin")
    public
}
