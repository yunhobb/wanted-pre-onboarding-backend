package com.wanted.backend.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.wanted.backend.global.dto.IdResponse;
import com.wanted.backend.member.entity.Member;
import com.wanted.backend.member.entity.dto.request.MemberRequest;
import com.wanted.backend.member.entity.dto.response.MemberResponse;
import com.wanted.backend.member.repository.MemberRepository;
import com.wanted.backend.member.service.MemberService;
import com.wanted.backend.support.DatabaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DatabaseTest
@DisplayName("Member 서비스의")
public class MemberServiceTest {

    @Autowired private MemberService memberService;
    @Autowired private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입이 되는가")
    void signUp() {
        //given
        String email = "yunho@naver.com";
        String password = "12345678";

        //when
        IdResponse<Long> response = memberService.signUp(new MemberRequest(email, password));

        //then
        Member memberEntity = memberRepository.findById(response.getId()).orElseThrow();

        assertThat(response.getId()).isEqualTo(memberEntity.getId());
    }

    @Test
    @DisplayName("로그인이 되는가")
    void signIn() {
        //given
        String email = "yunho@naver.com";
        String password = "12345678";

        //when
        MemberResponse response = memberService.signIn(new MemberRequest(email, password));

        //then
        assertThat(response.getToken()).isNotNull();
    }
}
