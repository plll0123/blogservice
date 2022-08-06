package com.blog.blogservice.controller;

import com.blog.blogservice.controller.dto.request.LoginForm;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.processor.annotation.Login;
import com.blog.blogservice.processor.annotation.RequiredLogin;
import com.blog.blogservice.service.InterceptorService;
import com.blog.blogservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final InterceptorService interceptorService;

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm){

        Member member = memberService.findMember(loginForm);

        interceptorService.login(member.getId());

        return "redirect:/";
    }

    @RequiredLogin
    @GetMapping("/test")
    public String login(@Login Member member){

        return "redirect:/";
    }

    @RequiredLogin
    @GetMapping("/logout")
    public void logout(){
        interceptorService.logout();
    }
}
