package com.blog.blogservice.controller.ex.custom;

import org.springframework.web.method.HandlerMethod;

import javax.validation.Valid;

public interface CustomReflection {

    default String getViewOfValidDto(HandlerMethod hm) {
        if(hm.hasMethodAnnotation(Valid.class))
            return findView(hm);

        return "redirect:/";
    }

    String findView(HandlerMethod hm);
}
