package com.wanted.backend.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCreateRequest {

    private final Long memberId;
    private final String title;
    private final String content;
}
