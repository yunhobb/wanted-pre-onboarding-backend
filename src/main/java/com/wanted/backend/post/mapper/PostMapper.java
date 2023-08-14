package com.wanted.backend.post.mapper;

import com.wanted.backend.member.entity.Member;
import com.wanted.backend.post.dto.request.PostCreateRequest;
import com.wanted.backend.post.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post toEntity(final PostCreateRequest request, final Member member) {
        return Post.builder()
            .content(request.getContent())
            .title(request.getTitle())
            .member(member)
            .build();
    }

}
