package com.blog.blogservice.service;

import com.blog.blogservice.controller.dto.request.PostCreate;
import com.blog.blogservice.controller.dto.response.PostResponse;
import com.blog.blogservice.domain.Post;
import com.blog.blogservice.domain.TimeEntity;
import com.blog.blogservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService extends TimeEntity {

    private final PostRepository postRepository;

//    public List<PostResponse> findPosts(Long blogId){
//        return null; /*postRepository.getPosts(blogId);*/
//    }
//
//    @Transactional
//    public void createPost(PostCreate postCreate){
//        postRepository.save(newPost(postCreate));
//    }
//
//    private Post newPost(PostCreate postCreate) {
//        return Post.builder()
//                .blog()
//                .title(postCreate.getTitle())
//                .content(postCreate.getContent())
//                .build();
//    }


}
