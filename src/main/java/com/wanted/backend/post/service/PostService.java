package com.wanted.backend.post.service;

import com.wanted.backend.global.EntityLoader;
import com.wanted.backend.global.config.jwt.JwtTokenProvider;
import com.wanted.backend.global.config.jwt.MemberTokenInfo;
import com.wanted.backend.global.dto.IdResponse;
import com.wanted.backend.global.exception.CustomJwtTokenException;
import com.wanted.backend.global.exception.EntityNotFoundException;
import com.wanted.backend.member.entity.Member;
import com.wanted.backend.member.exception.MemberNotFoundException;
import com.wanted.backend.member.repository.MemberRepository;
import com.wanted.backend.post.dto.request.PostCreateRequest;
import com.wanted.backend.post.dto.request.PostUpdateRequest;
import com.wanted.backend.post.entity.Post;
import com.wanted.backend.post.exception.PostNotFoundException;
import com.wanted.backend.post.mapper.PostMapper;
import com.wanted.backend.post.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostService implements EntityLoader<Post, Long>{

    PostRepository postRepository;
    PostMapper postMapper;
    MemberRepository memberRepository;
    JwtTokenProvider jwtTokenProvider;

    public IdResponse<Long> create(PostCreateRequest request) {
        Member member = existMember(request.getMemberId());
        Post post =  postRepository.save(postMapper.toEntity(request, member));
        return new IdResponse<>(post.getId());
    }

    public IdResponse<Long> update(PostUpdateRequest request, MemberTokenInfo info) {
        checkJwtToken(request.getPostId(),info);

        Post foundPost = existPost(request.getPostId());
        
        foundPost.updatePost(request.getContent(), request.getTitle());
        return new IdResponse<>(postRepository.save(foundPost).getId());
    }

    private Member existMember(final Long id) {
        return memberRepository.findById(id)
            .orElseThrow(MemberNotFoundException::new);
    }

    private Post existPost(final Long id) {
        return postRepository.findById(id)
            .orElseThrow(PostNotFoundException::new);
    }

    private void checkJwtToken(Long postId, MemberTokenInfo memberTokenInfo) {
        Post post = loadEntity(postId);

        if (!(post.getMember().getId()).equals(memberTokenInfo.getId())) {
            throw new CustomJwtTokenException();
        }
    }

    @Override
    public Post loadEntity(final Long id) {
        return postRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

}
