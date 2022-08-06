package com.blog.blogservice.controller.ex.custom;

import org.springframework.web.method.HandlerMethod;

import javax.validation.Valid;

public interface CustomReflection {

    default String getViewOfValidDto(HandlerMethod hm) {
        return findView(hm);
    }

    String findView(HandlerMethod hm);
}
