package com.wanted.backend.post.controller;

import com.wanted.backend.global.config.jwt.JwtAuthorization;
import com.wanted.backend.global.config.jwt.MemberTokenInfo;
import com.wanted.backend.global.dto.IdResponse;
import com.wanted.backend.post.entity.dto.reponse.PostPageResponse;
import com.wanted.backend.post.entity.dto.reponse.PostResponse;
import com.wanted.backend.post.entity.dto.request.PostCreateRequest;
import com.wanted.backend.post.entity.dto.request.PostUpdateRequest;
import com.wanted.backend.post.service.PostService;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostController {

    PostService postService;

    @PostMapping
    public ResponseEntity<IdResponse<Long>> create(
        @Valid @RequestBody PostCreateRequest request) {
        return ResponseEntity.ok(postService.create(request));
    }

    @PatchMapping
    public ResponseEntity<IdResponse<Long>> update(
        @Valid @RequestBody PostUpdateRequest request,
        @JwtAuthorization MemberTokenInfo memberTokenInfo) {
        return ResponseEntity.ok(postService.update(request, memberTokenInfo));
    }

    @GetMapping("/postid/{id}")
    public ResponseEntity<PostResponse> getOnePost(
        @PathVariable Long id) {
        return ResponseEntity.ok(postService.getOnePost(id));
    }

    @GetMapping("/page")
    public ResponseEntity<PostPageResponse> getPostByPagination(
        @RequestParam(defaultValue = "0") int offset,
        @RequestParam(defaultValue = "10") int size) {
        PostPageResponse response = postService.getPostByPagination(offset, size);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/postid/{id}")
    public ResponseEntity<Void> deletePost(
        @PathVariable Long id,
        @JwtAuthorization MemberTokenInfo memberTokenInfo) {
        postService.deletePost(id,memberTokenInfo);
        return ResponseEntity.ok().build();
    }
}
