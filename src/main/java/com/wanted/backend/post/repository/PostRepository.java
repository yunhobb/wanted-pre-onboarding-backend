package com.wanted.backend.post.repository;

import com.wanted.backend.post.entity.Post;

public interface PostRepository {
    Post save(Post post);
}
