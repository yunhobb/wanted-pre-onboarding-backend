package com.wanted.backend.post.dto.reponse;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPageResponse {

    private List<PostResponse> postResponses;
}
