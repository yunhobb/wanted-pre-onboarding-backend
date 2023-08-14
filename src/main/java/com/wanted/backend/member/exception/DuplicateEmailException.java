package com.wanted.backend.member.exception;

public class DuplicateEmailException extends IllegalStateException {
    private static final String MESSAGE = "이메일이 중복되었습니다.";

    public DuplicateEmailException() {
        super(MESSAGE);
    }
}
