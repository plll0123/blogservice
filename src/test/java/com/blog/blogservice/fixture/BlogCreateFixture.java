package com.blog.blogservice.fixture;

import com.blog.blogservice.controller.dto.request.BlogCreate;

public class BlogCreateFixture {

    public static BlogCreate blogDtoFixture(){
        return new BlogCreate("test", "test");
    }
}
