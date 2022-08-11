package com.blog.blogservice.controller;

import com.blog.blogservice.controller.dto.request.BlogCreate;
import com.blog.blogservice.controller.dto.request.PostCreate;
import com.blog.blogservice.processor.annotation.Login;
import com.blog.blogservice.processor.annotation.RequiredLogin;
import com.blog.blogservice.processor.interceptor.UserDetails;
import com.blog.blogservice.service.BlogService;
import com.blog.blogservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public String find(Long blogId, Model model) {
        model.addAttribute("blog", blogService.find(blogId));
        return "/blog/blogMain";
    }

    @RequiredLogin
    @GetMapping("/insert")
    public String createBlogForm(){
        return "/blog/insertBlog";
    }

    @RequiredLogin
    @PostMapping("/insert")
    public String register(@Valid BlogCreate blogCreate, @Login UserDetails userDetails) {
        Long blogId = blogService.create(userDetails.getMemberId(), blogCreate);
        userDetails.setBlogId(blogId);
        return "redirect:/";
    }

}
