package com.wanted.backend.post.service;

import com.wanted.backend.global.EntityLoader;
import com.wanted.backend.global.config.jwt.MemberTokenInfo;
import com.wanted.backend.global.dto.IdResponse;
import com.wanted.backend.global.exception.CustomJwtTokenException;
import com.wanted.backend.global.exception.EntityNotFoundException;
import com.wanted.backend.member.entity.Member;
import com.wanted.backend.member.exception.MemberNotFoundException;
import com.wanted.backend.member.repository.MemberRepository;
import com.wanted.backend.post.entity.dto.reponse.PostPageResponse;
import com.wanted.backend.post.entity.dto.reponse.PostResponse;
import com.wanted.backend.post.entity.dto.request.PostCreateRequest;
import com.wanted.backend.post.entity.dto.request.PostUpdateRequest;
import com.wanted.backend.post.entity.Post;
import com.wanted.backend.post.entity.mapper.PostMapper;
import com.wanted.backend.post.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostService implements EntityLoader<Post, Long>{

    PostRepository postRepository;
    PostMapper postMapper;
    MemberRepository memberRepository;

    public IdResponse<Long> create(final PostCreateRequest request) {
        Member member = existMember(request.getMemberId());
        Post post =  postRepository.save(postMapper.toEntity(request, member));
        return new IdResponse<>(post.getId());
    }

    public IdResponse<Long> update(final PostUpdateRequest request, final  MemberTokenInfo info) {
        checkJwtToken(request.getPostId(),info);

        Post foundPost = loadEntity(request.getPostId());

        foundPost.updatePost(request.getContent(), request.getTitle());
        return new IdResponse<>(postRepository.save(foundPost).getId());
    }

    public PostResponse getOnePost(final Long id) {
        Post post = loadEntity(id);
        return postMapper.toResponse(post);
    }

    public PostPageResponse getPostByPagination(final int offset, final int size) {
        PageRequest request = PageRequest.of(offset, size);
        Page<Post> postByPagenation = postRepository.findAll(request);
        return postMapper.toPageResponse(postByPagenation);
    }

    @Transactional
    public void deletePost(final Long id, final MemberTokenInfo info) {
        checkJwtToken(id, info);

        loadEntity(id).delete();
    }

    private Member existMember(final Long id) {
        return memberRepository.findById(id)
            .orElseThrow(MemberNotFoundException::new);
    }

    private void checkJwtToken(final Long postId, final MemberTokenInfo memberTokenInfo) {
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
