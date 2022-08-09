package com.blog.blogservice.repository;

import com.blog.blogservice.controller.dto.response.PostResponse;
import com.blog.blogservice.controller.dto.response.QPostResponse;
import com.blog.blogservice.domain.QBlog;
import com.blog.blogservice.domain.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.blog.blogservice.domain.QBlog.blog;
import static com.blog.blogservice.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory qFactory;

    @Override
    public List<PostResponse> findPosts(Long blogId, Pageable ag) {
        return null;
    }

    //    @Override
//    public List<PostResponse> getPosts(Long blogId) {
//        return qFactory.select(
//                new QPostResponse(
//                        post.title,
//                        post.content,
//                        post.updated)
//                )
//                .from(post)
//                .where(post.blog.id.eq(blogId))
//                .orderBy(post.id.desc())
//                .fetch();
//    }
}
