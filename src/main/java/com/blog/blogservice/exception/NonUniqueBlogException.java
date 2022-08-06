package com.blog.blogservice.exception;

public class NonUniqueBlogException extends BlogException {

    private static final String MESSAGE = "블로그는 하나만 생성할 수 있습니다.";
    private static final String VIEW_NAME = "blog/insertBlog";

    public NonUniqueBlogException(){
        super(MESSAGE);
    }

    @Override
    public String getView() {
        return VIEW_NAME;
    }
}
