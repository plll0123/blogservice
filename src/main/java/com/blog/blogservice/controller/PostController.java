package com.blog.blogservice.controller;

import com.blog.blogservice.controller.dto.request.PostCreate;
import com.blog.blogservice.domain.Blog;
import com.blog.blogservice.processor.annotation.Login;
import com.blog.blogservice.processor.annotation.RequiredLogin;
import com.blog.blogservice.processor.interceptor.UserDetails;
import com.blog.blogservice.service.BlogService;
import com.blog.blogservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final BlogService blogService;

    @RequiredLogin
    @PostMapping
    public String write(@Valid PostCreate postCreate, @Login UserDetails userDetails){
        blogService.writePost(userDetails.getMemberId(), postCreate);

        return "/blog/getBlog";
    }

}
