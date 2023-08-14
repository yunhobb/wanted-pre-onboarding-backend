package com.wanted.backend.global.exception;

public class CustomJwtTokenException extends IllegalStateException{
    private static final String MESSAGE = "토큰 검증에 실패했습니다.";

    public CustomJwtTokenException() {
        super(MESSAGE);
    }

}
