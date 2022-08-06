package com.blog.blogservice.exception;

public class IdOrPwMisMatch extends BlogException{

    private static final String MESSAGE = "ID 혹은 PW가 일치하지 않습니다.";
    private static final String VIEW_NAME = "welcome";

    public IdOrPwMisMatch() {
        super(MESSAGE);
    }

    @Override
    public String getView() {
        return VIEW_NAME;
    }
}
