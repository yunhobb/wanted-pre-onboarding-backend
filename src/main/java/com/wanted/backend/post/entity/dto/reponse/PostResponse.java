package com.wanted.backend.post.entity.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostResponse {

    private Long id;
    private String title;
    private String content;
}
