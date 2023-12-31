package com.wanted.backend.member.controller;

import com.wanted.backend.global.dto.IdResponse;
import com.wanted.backend.member.entity.dto.request.MemberRequest;
import com.wanted.backend.member.entity.dto.response.MemberResponse;
import com.wanted.backend.member.service.MemberService;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/sign-up")
    public ResponseEntity<IdResponse<Long>> signUp(
        @Valid @RequestBody MemberRequest request ) {
        return ResponseEntity.ok(memberService.signUp(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<MemberResponse> signIn(
        @Valid @RequestBody MemberRequest request ) {
        return ResponseEntity.ok(memberService.signIn(request));
    }
}
