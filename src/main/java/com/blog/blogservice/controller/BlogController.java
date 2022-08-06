package com.blog.blogservice.controller;

import com.blog.blogservice.processor.annotation.RequiredLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    @RequiredLogin
    @PostMapping
    public String register(){

        return null;
    }
}
