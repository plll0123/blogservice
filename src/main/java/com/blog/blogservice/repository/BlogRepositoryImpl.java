package com.blog.blogservice.repository;

import com.blog.blogservice.controller.dto.response.BlogMainResponse;
import com.blog.blogservice.controller.dto.response.PostResponse;
import com.blog.blogservice.controller.dto.response.QBlogMainResponse;
import com.blog.blogservice.controller.dto.response.QPostResponse;
import com.blog.blogservice.domain.QBlog;
import com.blog.blogservice.domain.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.blog.blogservice.domain.QBlog.blog;
import static com.blog.blogservice.domain.QPost.post;

@Repository("customRepository")
@RequiredArgsConstructor
public class BlogRepositoryImpl implements BlogRepositoryCustom {

    private final JPAQueryFactory qFactory;

    @Override
    public BlogMainResponse searchBy(Long blogId) {
        return qFactory.select(new QBlogMainResponse(
                        blog.id,
                        blog.title,
                        blog.tag
                ))
                .from(blog)
                .where(blog.id.eq(blogId))
                .fetchOne();
    }

    @Override
    public List<PostResponse> searchPostsBy(Long blogId) {
        return qFactory.select(new QPostResponse(
                post.title,
                post.content,
                post.updated
        ))
                .from(post)
                .where(post.id.eq(blogId))
                .fetch();
    }
}
