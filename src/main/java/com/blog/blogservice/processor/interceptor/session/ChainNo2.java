package com.blog.blogservice.processor.interceptor.session;

import com.blog.blogservice.domain.Member;
import com.blog.blogservice.service.InterceptorService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

import static com.blog.blogservice.processor.annotation.LoginConst.LOGGED_IN;
import static com.blog.blogservice.processor.annotation.LoginConst.MEMBER_ID;

@RequiredArgsConstructor
public class ChainNo2 implements SessionCheckChain {

    private final InterceptorService sessionService;

    @Override
    public boolean sessionCheck(HttpServletRequest request) {
        Long memberId = (Long) request.getAttribute(MEMBER_ID);

        Member loginMember = sessionService.getLoginMember(memberId);

        request.setAttribute(LOGGED_IN, loginMember);

        return true;
    }
}