package com.wanted.backend.post.repository;

import com.wanted.backend.post.entity.Post;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
}
