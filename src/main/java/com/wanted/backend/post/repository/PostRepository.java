package com.wanted.backend.post.repository;

import com.wanted.backend.post.entity.Post;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    Page<Post> findPostWithPagination(Pageable pageable);
}
