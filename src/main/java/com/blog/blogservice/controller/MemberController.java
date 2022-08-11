package com.blog.blogservice.controller;

import com.blog.blogservice.controller.dto.request.LoginForm;
import com.blog.blogservice.controller.dto.request.MemberCreator;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.processor.annotation.RequiredLogin;
import com.blog.blogservice.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @PostMapping("/join")
    public String join(@Valid MemberCreator memberCreator){
        memberService.join(memberCreator);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "/system/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm){

        Member member = memberService.login(loginForm);

        interceptorService.login(member);

        return "redirect:/";
    }

    @RequiredLogin
    @GetMapping("/logout")
    public String logout(){
        interceptorService.logout();
        return "/welcome";
    }

}
