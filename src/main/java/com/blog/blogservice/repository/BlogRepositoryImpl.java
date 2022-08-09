//package com.blog.blogservice.repository;
//
//import com.blog.blogservice.controller.dto.response.BlogMainResponse;
//import com.blog.blogservice.controller.dto.response.PostResponse;
//import com.blog.blogservice.controller.dto.response.QBlogMainResponse;
//import com.blog.blogservice.controller.dto.response.QPostResponse;
//import com.blog.blogservice.domain.Post;
//import com.blog.blogservice.exception.BlogNotFoundException;
//import com.querydsl.core.types.Expression;
//import com.querydsl.core.types.dsl.StringPath;
//import com.querydsl.jpa.impl.JPAQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//import java.util.Optional;
//
//import static com.blog.blogservice.domain.QBlog.*;
//import static com.blog.blogservice.domain.QPost.post;
//
//@RequiredArgsConstructor
//public class BlogRepositoryImpl implements BlogRepositoryCustom {
//
//    private final JPAQueryFactory qFactory;
//
////    @Override
////    public BlogMainResponse searchBy(Long id) {
////        return qFactory.select(
////                        new QBlogMainResponse(
////                                blog.id,
////                                blog.title,
////                                blog.tag))
////                .from(blog)
////                .where(blog.id.eq(id))
////                .fetchOne();
////    }
//
//    private List<PostResponse> getPostResponseList(Long blogId) {
//        return qFactory.select(new QPostResponse(
//                        post.title,
//                        post.content)
//                )
//                .from(post)
//                .where(post.blog.id.eq(blogId))
//                .fetch();
//    }
//
//}
