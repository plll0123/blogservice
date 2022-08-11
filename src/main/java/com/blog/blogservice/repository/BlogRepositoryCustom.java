package com.blog.blogservice.repository;

import com.blog.blogservice.controller.dto.response.BlogMainResponse;
import com.blog.blogservice.controller.dto.response.PostResponse;

import java.util.List;

public interface BlogRepositoryCustom {
    BlogMainResponse searchBy(Long blogId);

    List<PostResponse> searchPostsBy(Long blogId);
}
