package com.wanted.backend.post.controller;

import com.wanted.backend.global.dto.IdResponse;
import com.wanted.backend.post.dto.request.PostCreateRequest;
import com.wanted.backend.post.service.PostService;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostController {

    PostService postService;

    @PostMapping
    public ResponseEntity<IdResponse<Long>> create(
        @Valid @RequestBody PostCreateRequest request) {
        return ResponseEntity.ok(postService.create(request));
    }

  
}
