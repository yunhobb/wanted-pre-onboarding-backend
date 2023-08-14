package com.wanted.backend.member.service;

import com.wanted.backend.global.EntityLoader;
import com.wanted.backend.global.dto.IdResponse;
import com.wanted.backend.global.exception.EntityNotFoundException;
import com.wanted.backend.member.dto.request.MemberRequest;
import com.wanted.backend.member.entity.Member;
import com.wanted.backend.member.exception.DuplicateEmailException;
import com.wanted.backend.member.mapper.MemberMapper;
import com.wanted.backend.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MemberService implements EntityLoader<Member, Long> {
    MemberRepository memberRepository;
    MemberMapper memberMapper;

    @Transactional
    public IdResponse<Long> signUp(final MemberRequest request) {
        if(memberRepository.findByEmail(request.getEmail()).isPresent()){
            throw new DuplicateEmailException();
        }
        Member member = memberRepository.save(memberMapper.toEntity(request));
        return new IdResponse<>(member.getId());
    }



    @Override
    public Member loadEntity(final Long id) {
        return memberRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }
}
