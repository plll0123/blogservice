package com.blog.blogservice.exception;

public class MemberNotFoundException extends BlogException {

    private static final String MESSAGE = "회원을 찾을 수 없습니다.";
    private static final String VIEW_NAME = "system/login";

    public MemberNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public String getView() {
        return VIEW_NAME;
    }
}
