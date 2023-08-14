package com.wanted.backend.post.mapper;

import com.wanted.backend.member.entity.Member;
import com.wanted.backend.post.dto.reponse.PostPageResponse;
import com.wanted.backend.post.dto.reponse.PostResponse;
import com.wanted.backend.post.dto.request.PostCreateRequest;
import com.wanted.backend.post.entity.Post;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
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

    public PostResponse toResponse(final Post post) {
        return PostResponse.builder()
            .id(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .build();
    }

    public PostPageResponse toPageResponse(final Page<Post> postList) {
        List<PostResponse> postResponseList =
            postList.stream().map(this::toResponse).collect(Collectors.toList());
        return PostPageResponse.builder()
            .postResponses(postResponseList)
            .build();
    }

}
