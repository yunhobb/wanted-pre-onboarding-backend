package com.wanted.backend.post.service;

import com.wanted.backend.global.config.jwt.JwtTokenProvider;
import com.wanted.backend.global.dto.IdResponse;
import com.wanted.backend.member.entity.Member;
import com.wanted.backend.member.exception.MemberNotFoundException;
import com.wanted.backend.member.repository.MemberRepository;
import com.wanted.backend.post.dto.request.PostCreateRequest;
import com.wanted.backend.post.entity.Post;
import com.wanted.backend.post.mapper.PostMapper;
import com.wanted.backend.post.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostService {

    PostRepository postRepository;
    PostMapper postMapper;
    MemberRepository memberRepository;
    JwtTokenProvider jwtTokenProvider;

    public IdResponse<Long> create(PostCreateRequest request) {
        Member member = existMember(request.getMemberId());
        Post post =  postRepository.save(postMapper.toEntity(request, member));
        return new IdResponse<>(post.getId());
    }

    private Member existMember(final Long id) {
        return memberRepository.findById(id)
            .orElseThrow(MemberNotFoundException::new);
    }

}
