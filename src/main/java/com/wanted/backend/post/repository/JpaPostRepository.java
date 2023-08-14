package com.wanted.backend.post.repository;

import com.wanted.backend.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<Post, Long>, PostRepository{

}
