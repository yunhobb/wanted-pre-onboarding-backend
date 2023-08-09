package com.wanted.backend.member.exception;

public class EmailDuplicateException extends IllegalStateException {
    private static final String MESSAGE = "이메일이 중복되었습니다.";

    public EmailDuplicateException() {
        super(MESSAGE);
    }
}
