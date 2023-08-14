package com.wanted.backend.post.exception;

public class PostNotFoundException extends IllegalStateException {
    private static final String MESSAGE = "존재하지 않는 게시글 입니다.";

    public PostNotFoundException() {
        super(MESSAGE);
    }
}