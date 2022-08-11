package com.blog.blogservice.service;

import com.blog.blogservice.exception.BlogException;

public class UnAuthenticatedAccessException extends BlogException {

    private static final String MESSAGE = "로그인을 다시 해주세요";
    private static final String VIEW_NAME = "/system/login";
    public UnAuthenticatedAccessException() {
        super(MESSAGE);
    }

    @Override
    public String getView() {
        return VIEW_NAME;
    }
}
