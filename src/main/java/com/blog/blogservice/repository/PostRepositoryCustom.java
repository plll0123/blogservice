package com.blog.blogservice.repository;

import com.blog.blogservice.controller.dto.response.PostResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public interface PostRepositoryCustom {

    List<PostResponse> findPosts(Long blogId, Pageable pg);
}
