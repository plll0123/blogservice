package com.blog.blogservice.exception;

public abstract class BlogException extends RuntimeException{

    public BlogException(String message) {
        super(message);
    }

    public abstract String getView();

}
