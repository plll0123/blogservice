package com.blog.blogservice.controller;

import com.blog.blogservice.controller.dto.request.BlogCreate;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.processor.annotation.Login;
import com.blog.blogservice.processor.annotation.RequiredLogin;
import com.blog.blogservice.service.BlogService;
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
    public String find(Long blogId, Model model){
        model.addAttribute(blogService.findBlog(blogId));
        return "/blog/blogMain";
    }

    @RequiredLogin
    @PostMapping
    public String register(@Valid BlogCreate blogCreate, @Login Member member){
        blogService.create(member.getId(), blogCreate);
        return "redirect:/welcome";
    }


}
