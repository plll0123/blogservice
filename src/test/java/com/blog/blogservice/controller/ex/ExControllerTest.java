package com.blog.blogservice.controller.ex;

import com.blog.blogservice.domain.Blog;
import com.blog.blogservice.exception.BlogException;
import com.blog.blogservice.exception.IdOrPwMisMatch;
import com.blog.blogservice.exception.MemberNotFoundException;
import com.blog.blogservice.exception.NonUniqueBlogException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExControllerTest {

    private static List<Class<?>> exList = new ArrayList<>();

    @Test
    void exception() {
        exList.add(BlogException.class);
        exList.add(IdOrPwMisMatch.class);
        exList.add(MemberNotFoundException.class);
        exList.add(NonUniqueBlogException.class);


    }
}