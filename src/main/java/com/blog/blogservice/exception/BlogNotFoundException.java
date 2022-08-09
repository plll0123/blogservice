package com.blog.blogservice.exception;

public class BlogNotFoundException extends BlogException{

    private final static String MESSAGE = "해당 블로그를 찾을 수 없습니다.";
    private final static String VIEW_NAME = "/welcome";

    public BlogNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public String getView() {
        return VIEW_NAME;
    }
}
