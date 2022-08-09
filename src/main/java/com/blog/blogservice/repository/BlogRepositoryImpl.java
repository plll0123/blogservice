package com.blog.blogservice.repository;

import com.blog.blogservice.controller.dto.response.BlogMainResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

//@Repository("customRepository")
@RequiredArgsConstructor
public class BlogRepositoryImpl implements BlogRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public BlogMainResponse findByByBy(Long blogId) {
        return null;
    }
}
