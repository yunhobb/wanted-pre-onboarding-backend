package com.wanted.backend.post.repository;

import com.wanted.backend.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<Post, Long>, PostRepository{

    @Query("select r from Post r where r.deleted_at is null")
    Page<Post> findPostWithPagination(Pageable pageable);
}
