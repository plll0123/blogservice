package com.blog.blogservice.repository;

import com.blog.blogservice.controller.dto.response.BlogMainResponse;

public interface BlogRepositoryCustom {
    BlogMainResponse findBlogMain(Long blogId);

}
