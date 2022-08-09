package com.blog.blogservice.repository;

import com.blog.blogservice.controller.dto.response.BlogMainResponse;
import com.blog.blogservice.domain.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlogRepositoryCustom {

    Optional<Blog> searchOne(Long blogId);
}
