package com.wanted.backend.member.exception;

public class MemberNotFoundException extends IllegalStateException {
    private static final String MESSAGE = "존재하지 않은 유저입니다.";

    public MemberNotFoundException() {
        super(MESSAGE);
    }
}


